package com.foolver.juo;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.engine.Engine;
import com.foolver.juo.engine.exception.EngineException;

public class Juo {

  private static final Logger log = LoggerFactory.getLogger(Juo.class);

  private static final int MAX_THREADS = 1000;
  
  private static Engine engine;

  public static void main(String[] args) throws EngineException {
    ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
    engine = new Engine();
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
      Runnable clientHandler = new ClientHandler(serverSocket.accept(), engine);
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
