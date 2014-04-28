package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class PingMessageHandler extends AbstractRequestHandler<EmptyPacket> {

  private static final Logger log = LoggerFactory.getLogger(PingMessageHandler.class);
  
  @Override
  public EmptyPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      //TODO: receive a ping with the seq number, need to reply with the proper seq number
      log.info("################################################################");
      log.info("####################IMPLEMENT ME PLEASE#########################");
      log.info("################################################################");
      skypBytes(is, 1); // sequence number
      return new EmptyPacket();
    });
  }
}
