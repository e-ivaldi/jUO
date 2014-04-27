package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.DeleteCharacterPacket;

public class DeleteCharacterHandler extends AbstractRequestHandler<DeleteCharacterPacket> {

  @Override
  public DeleteCharacterPacket handle(InputStream is) throws PacketHandlingException {

    String password;
    int charIndex;
    int clientIp;

    try {

      password = getFixedSizeStringFromInputStream(is, 30);
      charIndex = getIntFromInputStream(is);
      clientIp = getIntFromInputStream(is);

    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }

    return new DeleteCharacterPacket(password, charIndex, clientIp);

  }

}
