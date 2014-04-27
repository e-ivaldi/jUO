package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class ClientSpyHandler extends AbstractRequestHandler<EmptyPacket> {

  @Override
  public EmptyPacket handle(InputStream is) throws PacketHandlingException {

    try {
      getFixedSizeStringFromInputStream(is, 148);
    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }

    return new EmptyPacket();
  }

}
