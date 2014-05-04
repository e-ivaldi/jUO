package com.foolver.juo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.PacketProcessorDispatcher;
import com.foolver.juo.packetHandling.packets.processors.PacketProcessor;
import com.foolver.juo.packetHandling.request2packet.RequestDispatcher;
import com.foolver.juo.packetHandling.request2packet.handlers.RequestHandler;
import com.foolver.juo.util.ByteUtil;
import com.foolver.juo.util.DataReader;
import com.foolver.juo.util.SimpleDataReader;

public class ClientHandler implements Runnable {

  private static final Logger log = LoggerFactory.getLogger(ClientHandler.class);

  private Socket client;
  private DataReader dataReader;
  private OutputStream os;
  private RequestDispatcher requestDispatcher;
  private PacketProcessorDispatcher packetProcessorDispatcher;
  private byte previousPacketId = 0x01;

  public ClientHandler(Socket client, RequestDispatcher packetDispatcher,
      PacketProcessorDispatcher packetProcessorDispatcher) {
    this.client = client;
    this.requestDispatcher = packetDispatcher;
    this.packetProcessorDispatcher = packetProcessorDispatcher;
    setupStreams(client);
  }

  private void setupStreams(Socket client) {
    try {
      dataReader = new SimpleDataReader(client.getInputStream());
      os = client.getOutputStream();
    } catch (IOException e) {
      log.error("unable to get stream for the client", e);
    }
  }

  public void run() {
    try {
      skeepSeedPacket();
      while (!threadIsInterrupted()) {
        readInputAndWriteOutput();
      }
    } catch (IOException e) {
      log.error("unable to read byte from the input stream", e);
    } catch (PacketHandlingException e) {
      log.error("problems happened during the packet handling..", e);
    } finally {
      log.info("closing the socket connection");
      closeClientSocket();
    }
  }

  @SuppressWarnings("unchecked")
  private void readInputAndWriteOutput() throws IOException, PacketHandlingException {
    byte packetId = readPacketId(); 
    RequestHandler<?> requestHandler = requestDispatcher.dispatch(packetId);
    Packet requestPacket = requestHandler.handle(dataReader);
    PacketProcessor<Packet> packetProcessor = packetProcessorDispatcher.getPacketProcessor(requestPacket);
    logPacketProcessorInfo(packetId, packetProcessor);
    Packet responsePacket = packetProcessor.processPacket(requestPacket);
    writeAndFlushTheResponsePacket(responsePacket);
    
  }

  private void logPacketProcessorInfo(byte packetId, PacketProcessor<? extends Packet> packetProcessor) {
    log.info(String.format("using packetProcessor %s for packet %s", packetProcessor.getClass().getSimpleName(),
        ByteUtil.getPrintable(packetId)));
  }

  private void skeepSeedPacket() throws IOException {
    dataReader.skip(4);
  }

  private void killThread() {
    log.info("killing the thread");
    Thread.currentThread().interrupt();
  }

  private byte readPacketId() throws IOException, PacketHandlingException {
    byte packetId = dataReader.readByte();
    log.info(String.format("read packet: %s", ByteUtil.getPrintable(packetId)));
    if (packetId == 0 && previousPacketId == 0) {
      log.info("client disconnected, killing the thread..");
      killThread();
    }
    previousPacketId = packetId;
    return packetId;
  }

  private void writeAndFlushTheResponsePacket(Packet responsePacket) throws IOException {
    if (responsePacket.hasBytes()) {
      log.info(String.format("sending packet: %s", ByteUtil.getPrintable(responsePacket.getBytes())));
      os.write(responsePacket.getBytes());
      os.flush();
    }
  }

  private boolean threadIsInterrupted() {
    return Thread.currentThread().isInterrupted();
  }

  private void closeClientSocket() {
    try {
      client.close();
    } catch (IOException e) {
      log.error("unable to shutdown the socket");
    }
  }

}
