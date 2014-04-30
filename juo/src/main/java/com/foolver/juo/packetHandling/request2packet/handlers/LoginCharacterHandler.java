package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.LoginCharacterPacket;
import com.foolver.juo.util.DataReader;

public class LoginCharacterHandler extends AbstractRequestHandler<LoginCharacterPacket> {

  @Override
  public LoginCharacterPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      dataReader.skip(4); // pattern (?)
        String charName = dataReader.readString(30);
        dataReader.skip(2); // unknown
        int clientFlag = dataReader.readInt();
        dataReader.skip(4); // unknown
        dataReader.skip(4); // logincount
        dataReader.skip(16); // unknown
        int slotChosen = dataReader.readInt();
        dataReader.skip(4); // clientIp
        return new LoginCharacterPacket(charName, slotChosen, clientFlag);
      });

  }
}
