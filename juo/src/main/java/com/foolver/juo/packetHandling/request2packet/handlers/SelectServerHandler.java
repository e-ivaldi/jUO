package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.SelectServerPacket;

public class SelectServerHandler extends AbstractRequestHandler<SelectServerPacket> {

  @Override
  public SelectServerPacket handle(InputStream is) throws PacketHandlingException {

    short serverIndex;

    try {
      serverIndex = getShortFromInputStream(is);
    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }

    return new SelectServerPacket(serverIndex);
  }

}
