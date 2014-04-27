package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.LoginCharacterPacket;

public class LoginCharacterHandler extends AbstractRequestHandler<LoginCharacterPacket> {

  @Override
  public LoginCharacterPacket handle(InputStream is) throws PacketHandlingException {

    String charName;
    int clientFlag;
    int slotChosen;

    try {

      skypBytes(is, 4); // pattern (?)
      charName = getFixedSizeStringFromInputStream(is, 30);
      skypBytes(is, 2); // unknown
      clientFlag = getIntFromInputStream(is);
      skypBytes(is, 4); // unknown
      skypBytes(is, 4); // logincount
      skypBytes(is, 16); // unknown
      slotChosen = getIntFromInputStream(is);
      skypBytes(is, 4); // clientIp

    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }

    return new LoginCharacterPacket(charName, slotChosen, clientFlag);

  }

}
