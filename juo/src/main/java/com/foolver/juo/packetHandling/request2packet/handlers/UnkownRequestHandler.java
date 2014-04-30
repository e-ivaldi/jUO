package com.foolver.juo.packetHandling.request2packet.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.util.DataReader;

public class UnkownRequestHandler extends AbstractRequestHandler<EmptyPacket> {

  private static final Logger log = LoggerFactory.getLogger(UnkownRequestHandler.class);

  @Override
  public EmptyPacket handle(DataReader dataReader) {
    log.warn("default request handler in action, this means that a packet handler is missed");
    return new EmptyPacket();
  }

}
