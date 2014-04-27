package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.SelectServerPacket;

public class SelectServerHandler extends AbstractRequestHandler<SelectServerPacket> {

  @Override
  public SelectServerPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      short serverIndex = getShortFromInputStream(is);
      return new SelectServerPacket(serverIndex);
    });
  }

}
