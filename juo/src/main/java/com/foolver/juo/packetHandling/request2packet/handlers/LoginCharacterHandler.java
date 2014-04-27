package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.LoginCharacterPacket;

public class LoginCharacterHandler extends AbstractRequestHandler<LoginCharacterPacket> {

  @Override
  public LoginCharacterPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      skypBytes(is, 4); // pattern (?)
        String charName = getFixedSizeStringFromInputStream(is, 30);
        skypBytes(is, 2); // unknown
        int clientFlag = getIntFromInputStream(is);
        skypBytes(is, 4); // unknown
        skypBytes(is, 4); // logincount
        skypBytes(is, 16); // unknown
        int slotChosen = getIntFromInputStream(is);
        skypBytes(is, 4); // clientIp
        return new LoginCharacterPacket(charName, slotChosen, clientFlag);
      });

  }

}
