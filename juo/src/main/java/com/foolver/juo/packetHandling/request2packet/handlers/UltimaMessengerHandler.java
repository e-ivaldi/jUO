package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.UltimaMessengerPacket;

public class UltimaMessengerHandler extends AbstractRequestHandler<UltimaMessengerPacket> {

  @Override
  public UltimaMessengerPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      int id1 = getIntFromInputStream(is);
      int id2 = getIntFromInputStream(is);
      return new UltimaMessengerPacket(id1, id2);
    });
  }
}
