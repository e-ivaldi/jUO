package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.GraphicalEffectPacket;

public class GraphicalEffectHandler extends AbstractRequestHandler<GraphicalEffectPacket> {

  @Override
  public GraphicalEffectPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      //TODO: this is completely wrong, fix it
      getFixedSizeStringFromInputStream(is, 3);
      return new GraphicalEffectPacket();
    });
  }

}
