package com.foolver.juo.packetHandling.request2packet;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.request2packet.handlers.ClientSpyHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.DeleteCharacterHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.GameServerLoginHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.GraphicalEffectHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.LoginCharacterHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.LoginRequestHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.MoveRequestHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.RequestHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.RestartVersionHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.SelectServerHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.ServerListRemoveEntryHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.SpeechRequestHandler;
import com.foolver.juo.packetHandling.request2packet.handlers.UnkownRequestHandler;

public class RequestDispatcher {

  private static final Logger log = LoggerFactory.getLogger(RequestDispatcher.class);
  private static final Map<Byte, RequestHandler<? extends Packet>> requestHandlers = new HashMap<>();

  private static final RequestHandler<? extends Packet> defaultHandler = new UnkownRequestHandler();
  
  public RequestDispatcher() {
    requestHandlers.put((byte) 0x80, new LoginRequestHandler());
    requestHandlers.put((byte) 0x60, new ServerListRemoveEntryHandler());
    requestHandlers.put((byte) 0x5C, new RestartVersionHandler());
    requestHandlers.put((byte) 0xA0, new SelectServerHandler());
    requestHandlers.put((byte) 0xA4, new ClientSpyHandler());
    requestHandlers.put((byte) 0xC0, new GraphicalEffectHandler());
    requestHandlers.put((byte) 0x91, new GameServerLoginHandler());
    requestHandlers.put((byte) 0x5D, new LoginCharacterHandler());
    requestHandlers.put((byte) 0x83, new DeleteCharacterHandler());
    requestHandlers.put((byte) 0x02, new MoveRequestHandler());
    requestHandlers.put((byte) 0xAD, new SpeechRequestHandler());
  }

  public RequestHandler<? extends Packet> dispatch(Byte packetId) {
    RequestHandler<? extends Packet> handler = requestHandlers.get(packetId);
    if (handler == null) {
      log.error(String.format("unable to find a request handler for the packetId %s", packetId));
      handler = defaultHandler;
    }
    return handler;
  }
}
