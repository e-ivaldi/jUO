package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class UnkownRequestHandler extends AbstractRequestHandler<EmptyPacket> {

  private static final Logger log = LoggerFactory.getLogger(UnkownRequestHandler.class);

  @Override
  public EmptyPacket handle(InputStream is) {
    log.warn("default request handler in action, this means that a packet handler is missed");
    return new EmptyPacket();
  }

}
