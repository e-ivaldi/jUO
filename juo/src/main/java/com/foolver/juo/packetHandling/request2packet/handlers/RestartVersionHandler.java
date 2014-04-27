package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.RestartVersionPacket;

public class RestartVersionHandler extends AbstractRequestHandler<RestartVersionPacket> {

  @Override
  public RestartVersionPacket handle(InputStream is) throws PacketHandlingException {
    try {
      getFixedSizeStringFromInputStream(is, 1);
    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }
    return new RestartVersionPacket();
  }

}
