package com.foolver.juo;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.packets.PacketProcessorDispatcher;
import com.foolver.juo.packetHandling.request2packet.RequestDispatcher;

public class Juo {

  private static final Logger log = LoggerFactory.getLogger(Juo.class);
  private static final RequestDispatcher requestDispatcher = new RequestDispatcher();
  private static final PacketProcessorDispatcher packetProcessorDispatcher = new PacketProcessorDispatcher();

  private static final int MAX_THREADS = 1000;

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
    createServerSocketAndWaitForClients(executor);
  }

  private static void createServerSocketAndWaitForClients(ExecutorService executor) {
    ServerSocket serverSocket = null;
    try {
      serverSocket = new ServerSocket(2593);
      waitForClients(serverSocket, executor);
    } catch (IOException e) {
      log.error("I/O exception", e);
    } finally {
      closeServerSocket(serverSocket);
    }
  }

  private static void waitForClients(ServerSocket serverSocket, ExecutorService executor) throws IOException {
    log.info("Waiting for clients..");
    while (true) {
      Runnable clientHandler = new ClientHandler(serverSocket.accept(), requestDispatcher, packetProcessorDispatcher);
      executor.execute(clientHandler);
    }
  }

  private static void closeServerSocket(ServerSocket serverSocket) {
    try {
      serverSocket.close();
    } catch (IOException e) {
      log.error("unable to close the server socket", e);
    }
  }

}
