package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

public class RequestSkillHandler extends AbstractRequestHandler<EmptyPacket> {

  @Override
  public EmptyPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      // TODO: implement this properly
        short blockSize = getShortFromInputStream(is);
        byte type = getByteFromInputStream(is);
        skypBytes(is, blockSize - 3);
        return new EmptyPacket();
      });

  }

}
