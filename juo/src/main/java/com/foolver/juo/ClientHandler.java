package com.foolver.juo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.processors.PacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.dispatcher.PacketProcessorDispatcher;
import com.foolver.juo.packetHandling.request2packet.RequestDispatcher;
import com.foolver.juo.packetHandling.request2packet.handlers.RequestHandler;
import com.foolver.juo.util.ByteUtil;

public class ClientHandler implements Runnable {

  private static final Logger log = LoggerFactory.getLogger(ClientHandler.class);

  private Socket client;
  private InputStream is;
  private OutputStream os;
  private RequestDispatcher requestDispatcher;
  private PacketProcessorDispatcher packetProcessorDispatcher;
  private byte previousPacketId = 0x01;

  public ClientHandler(Socket client, RequestDispatcher packetDispatcher,
      PacketProcessorDispatcher packetProcessorDispatcher) {
    this.client = client;
    this.requestDispatcher = packetDispatcher;
    this.packetProcessorDispatcher = packetProcessorDispatcher;
    try {
      client.setSoTimeout(Integer.MAX_VALUE);
      client.setKeepAlive(true);
      client.setTcpNoDelay(true);
    } catch (SocketException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    setupStreams(client);
  }

  private void setupStreams(Socket client) {
    try {
      is = client.getInputStream();
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
    if (isValidPacketId(packetId)) {
      RequestHandler<?> requestHandler = requestDispatcher.dispatch(packetId);
      Packet requestPacket = requestHandler.handle(is);
      PacketProcessor<Packet> packetProcessor = packetProcessorDispatcher.getPacketProcessor(requestPacket);
      Packet responsePacket = packetProcessor.processPacket(requestPacket);
      writeAndFlushTheResponsePacket(responsePacket);
    }
  }

  private void skeepSeedPacket() throws IOException {
    is.skip(4);
  }

  private void killThread() {
    log.info("killing the thread");
    Thread.currentThread().interrupt();
  }

  private boolean isValidPacketId(byte packetId) {
    return packetId != 0x00;
  }

  private byte readPacketId() throws IOException, PacketHandlingException {
    byte[] packetId = new byte[1];
    log.info(String.format("read packet: %s", packetId[0]));
    is.read(packetId);
    if (packetId[0] == 0 && previousPacketId == 0) {
      log.info("client disconnected, killing the thread..");
      killThread();
    }
    previousPacketId = packetId[0];
    return packetId[0];
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
