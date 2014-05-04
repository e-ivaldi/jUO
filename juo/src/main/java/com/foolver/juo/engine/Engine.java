package com.foolver.juo.engine;

import java.io.IOException;

import com.foolver.juo.engine.exception.EngineException;
import com.foolver.juo.engine.map.MapReader;
import com.foolver.juo.packetHandling.packets.PacketProcessorDispatcher;
import com.foolver.juo.packetHandling.request2packet.RequestDispatcher;

public class Engine {

  private final RequestDispatcher requestDispatcher;
  private final PacketProcessorDispatcher packetProcessorDispatcher;
  private final MapReader mapReader;

  public Engine() throws EngineException {
    try {
      mapReader = new MapReader();
    } catch (IOException e) {
      throw new EngineException("unable to instantiate the map reader", e);
    }
    requestDispatcher = new RequestDispatcher();
    packetProcessorDispatcher = new PacketProcessorDispatcher(mapReader);
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
  
  

}
