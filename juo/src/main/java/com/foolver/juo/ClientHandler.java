package com.foolver.juo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.processors.PacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.dispatcher.PacketProcessorDispatcher;
import com.foolver.juo.packetHandling.packets.response.DisconnectNotificationPacket;
import com.foolver.juo.packetHandling.request2packet.RequestDispatcher;
import com.foolver.juo.packetHandling.request2packet.handlers.RequestHandler;
import com.foolver.juo.util.ByteUtil;

public class ClientHandler implements Runnable {

  private static final Logger log = LoggerFactory.getLogger(ClientHandler.class);

  private static final long THREAD_SLEEP_WAITING_FOR_DATA_IN_MILLIS = 250;
  private static final long CLIENT_TIMOUT_IN_MILLIS = 60000;

  private Socket client;
  private InputStream is;
  private OutputStream os;
  private RequestDispatcher requestDispatcher;
  private PacketProcessorDispatcher packetProcessorDispatcher;
  private long lastByteReceivedInMillis = 0;

  public ClientHandler(Socket client, RequestDispatcher packetDispatcher,
      PacketProcessorDispatcher packetProcessorDispatcher) {
    this.client = client;
    this.requestDispatcher = packetDispatcher;
    this.packetProcessorDispatcher = packetProcessorDispatcher;
    setupStreams(client);
  }

  private void setupStreams(Socket client) {
    try {
      is = new BufferedInputStream(client.getInputStream());
      os = client.getOutputStream();
    } catch (IOException e) {
      log.error("unable to get stream for the client", e);
    }
  }

  public void run() {
    try {
      skeepSeedPacket();
      while (!threadIsInterrupted()) {
        handleClientTimeoutException();
        readInputAndWriteOutput();
      }
    } catch (IOException e) {
      log.error("unable to read byte from the input stream", e);
    } catch (PacketHandlingException e) {
      log.error("problems happened during the packet handling..", e);
    } finally {
      log.info("closing the socket connection");
      closeClientSocket();
      killThread();
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

  private void handleClientTimeoutException() throws IOException, PacketHandlingException {
    try {
      while (is.available() == 0) {
        long now = System.currentTimeMillis();
        if (lastByteReceivedInMillis == 0) {
          lastByteReceivedInMillis = now;
        } else {
          if (now > lastByteReceivedInMillis + CLIENT_TIMOUT_IN_MILLIS) {
            writeAndFlushTheResponsePacket(new DisconnectNotificationPacket());
            throw new PacketHandlingException("client timeout..");
          }
        }
        Thread.sleep(THREAD_SLEEP_WAITING_FOR_DATA_IN_MILLIS);
      }
      lastByteReceivedInMillis = 0;
    } catch (InterruptedException e) {
      throw new PacketHandlingException("problems sleeping the thread", e);
    }
  }

  private void skeepSeedPacket() throws IOException {
    is.skip(4);
  }

  private void killThread() {
    Thread.currentThread().interrupt();
  }

  private boolean isValidPacketId(byte packetId) {
    return packetId != (byte) 0x00;
  }

  private byte readPacketId() throws IOException {
    byte[] packetId = new byte[1];
    is.read(packetId);
    log.info(String.format("received packet: %s", ByteUtil.getPrintable(packetId)));
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
