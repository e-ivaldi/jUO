package com.foolver.juo.engine;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.engine.exception.EngineException;
import com.foolver.juo.engine.map.MapReader;
import com.foolver.juo.packetHandling.packets.PacketProcessorDispatcher;
import com.foolver.juo.packetHandling.request2packet.RequestDispatcher;

public class Engine {

  private static final Logger log = LoggerFactory.getLogger(Engine.class);
  private static final int DEFAULT_IP_ADDRESS = 16; // 127.0.0.1

  private final RequestDispatcher requestDispatcher;
  private final PacketProcessorDispatcher packetProcessorDispatcher;
  private final MapReader mapReader;
  private final Notificator world;
  
  private final List<String> remoteIpServices = Arrays.asList(
      "http://checkip.amazonaws.com/",
      "http://icanhazip.com/",
      "http://curlmyip.com/",
      "http://www.trackip.net/ip");
  private int exposedIp;

  public Engine() throws EngineException {
    try {
      mapReader = new MapReader();
      setupExposedIp();
    } catch (IOException e) {
      throw new EngineException("unable to instantiate engine", e);
    }
    world = new Notificator();
    requestDispatcher = new RequestDispatcher();
    packetProcessorDispatcher = new PacketProcessorDispatcher(this);
  }
  
  public MapReader getMapReader() {
    return mapReader;
  }

  public RequestDispatcher getRequestDispatcher() {
    return requestDispatcher;
  }

  public PacketProcessorDispatcher getPacketProcessorDispatcher() {
    return packetProcessorDispatcher;
  }

  public int getExposedIp() {
    return exposedIp;
  }  

  public Notificator getWorld() {
    return world;
  }
  
  private void setupExposedIp() throws SocketException {
    int tryNumb = 0;
    do {
      exposedIp = getExternalIpFromAmazon(remoteIpServices.get(tryNumb));
    } while (exposedIp == DEFAULT_IP_ADDRESS && tryNumb++ < remoteIpServices.size());
    log.info(String.format("Using exposedIp: %s from service %s", exposedIp, remoteIpServices.get(tryNumb)));
  }
  
  private int getExternalIpFromAmazon(String readeableUrl) {
    int ip = DEFAULT_IP_ADDRESS;
    Scanner scanner = null;
    try {
      URL url = new URL(readeableUrl);
      scanner = new Scanner(url.openStream());
      String redeableIp = scanner.next().trim();
      List<Integer> splittedIp = getListOfRedeableIp(redeableIp);
      // TODO: this is  common problem, extract this logic to a proper class (ByteUtil?)42
      ip = 24 << splittedIp.get(0) | 16 << splittedIp.get(1) | 8 << splittedIp.get(2) | splittedIp.get(3);
    } catch (MalformedURLException e) {
      log.warn("malformed url..");
    } catch (IOException e) {
      log.warn("i/o exception getting the ip from the external service..");
    } finally {
      if(scanner != null){
        scanner.close();
      }
    }
    return ip;
  }
  
  private List<Integer> getListOfRedeableIp(String redeableIp) {
    return Arrays.asList(redeableIp.split("\\.")).stream().map(Integer::parseInt)
        .collect(Collectors.toList());
  }

}
