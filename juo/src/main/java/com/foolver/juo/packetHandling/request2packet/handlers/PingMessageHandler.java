package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class PingMessageHandler extends AbstractRequestHandler<EmptyPacket> {

  @Override
  public EmptyPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      return new EmptyPacket();
    });
  }
}
