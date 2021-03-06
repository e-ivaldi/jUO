package com.foolver.juo;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.engine.Engine;
import com.foolver.juo.game.PlayerInfo;
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
  private BlockingQueue<Packet> responseQueue = new LinkedBlockingDeque<>();
  private Thread queueManagerThread;
  private PlayerInfo playerInfo;

  public ClientHandler(Socket client, Engine engine) {
    this.playerInfo = new PlayerInfo();
    this.client = client;
    this.requestDispatcher = engine.getRequestDispatcher();
    this.packetProcessorDispatcher = engine.getPacketProcessorDispatcher();
    engine.getWorld().registerClientHandler(this);
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
    startQueueManager();
    try {
      skeepSeedPacket();
      while (!Thread.currentThread().isInterrupted()) {
        readInputAndWriteOutput();
      }
    } catch (EOFException e) {
      log.info(String.format("client %s disconnected", client.getLocalAddress()));
    } catch (IOException e) {
      log.error("unable to read byte from the input stream", e);
    } catch (PacketHandlingException e) {
      log.error("problems happened during the packet handling..", e);
    } finally {
      log.info("closing the socket connection");
      closeClientSocket();
      queueManagerThread.interrupt();
    }
  }

  public void addToResponseQueue(Packet responsePacket) {
    responseQueue.add(responsePacket);
  }
  
  private void startQueueManager() {
    queueManagerThread = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        Packet p;
        try {
          p = responseQueue.take();
          if (p.hasBytes()) {
            log.info(String.format("sending packet: %s", ByteUtil.getPrintable(p.getBytes())));
            os.write(p.getBytes());
            os.flush();
          }
        } catch (IOException e) {
          log.error("unable to write the packet to the output stream");
        } catch (Exception e) {
          Thread.currentThread().interrupt();
        }
      }
    });
    queueManagerThread.start();
  }

  @SuppressWarnings("unchecked")
  private void readInputAndWriteOutput() throws IOException, PacketHandlingException {
    byte packetId = readPacketId();
    RequestHandler<?> requestHandler = requestDispatcher.dispatch(packetId);
    Packet requestPacket = requestHandler.handle(dataReader);
    PacketProcessor<Packet> packetProcessor = packetProcessorDispatcher.getPacketProcessor(requestPacket);
    logPacketProcessorInfo(packetId, packetProcessor);
    Packet responsePacket = packetProcessor.processPacket(playerInfo, requestPacket);
    addToResponseQueue(responsePacket);
  }

  private void logPacketProcessorInfo(byte packetId, PacketProcessor<? extends Packet> packetProcessor) {
    log.info(String.format("using packetProcessor %s for packet %s", packetProcessor.getClass().getSimpleName(),
        ByteUtil.getPrintable(packetId)));
  }

  private void skeepSeedPacket() throws IOException {
    dataReader.skip(4);
  }

  private byte readPacketId() throws IOException, PacketHandlingException {
    byte packetId = dataReader.readByte();
    log.info(String.format("read packet: %s", ByteUtil.getPrintable(packetId)));
    return packetId;
  }

  private void closeClientSocket() {
    try {
      client.shutdownInput();
      client.shutdownOutput();
      client.close();
    } catch (IOException e) {
      log.error("unable to shutdown the socket");
    }
  }

  public PlayerInfo getPlayerInfo() {
    return playerInfo;
  }

}
