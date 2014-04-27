package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.DeleteCharacterPacket;

public class DeleteCharacterHandler extends AbstractRequestHandler<DeleteCharacterPacket> {

  @Override
  public DeleteCharacterPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      String password = getFixedSizeStringFromInputStream(is, 30);
      int charIndex = getIntFromInputStream(is);
      int clientIp = getIntFromInputStream(is);
      return new DeleteCharacterPacket(password, charIndex, clientIp);
    });

  }

}
