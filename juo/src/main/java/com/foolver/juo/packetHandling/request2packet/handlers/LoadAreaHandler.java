package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class LoadAreaHandler extends AbstractRequestHandler<EmptyPacket> {

  @Override
  public EmptyPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      // TODO: check what kind of response this request needs
        this.skypBytes(is, 12); // unknown
        return new EmptyPacket();
      });

  }

}
