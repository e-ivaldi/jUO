package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.CreateCharacterPacket;
import com.foolver.juo.util.DataReader;

public class CreateCharacterHandler extends AbstractRequestHandler<CreateCharacterPacket> {

  @Override
  public CreateCharacterPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      dataReader.skipInt();// pattern1
      dataReader.skipInt();// pattern2
      dataReader.skipByte();// pattern 3
      String charName = dataReader.readString(30);
      dataReader.skipShort(); // unknown
      int clientFlag = dataReader.readInt();
      dataReader.skipInt(); //  unknown
      int loginCount = dataReader.readInt();
      byte profession = dataReader.readByte();
      dataReader.skip(15); // unknown
      byte gender = dataReader.readByte();
      byte strength = dataReader.readByte();
      byte dexterity = dataReader.readByte();
      byte intelligence = dataReader.readByte();
      byte skill1 = dataReader.readByte();
      byte skill1Value = dataReader.readByte();
      byte skill2 = dataReader.readByte();
      byte skill2Value = dataReader.readByte();
      byte skill3 = dataReader.readByte();
      byte skill3Value = dataReader.readByte();
      short skinColor = dataReader.readShort();
      short hairStyle = dataReader.readShort();
      short hairColor = dataReader.readShort();
      short facialHair = dataReader.readShort();
      short facialHairColor = dataReader.readShort();
      short location = dataReader.readShort(); // from starting list!
      dataReader.skipShort(); // unknown, generally 0x00
      short slot = dataReader.readShort();
      int clientIp = dataReader.readInt();
      short shirtColor = dataReader.readShort();
      short pantsColor = dataReader.readShort();
      return new CreateCharacterPacket();
      });

  }

}
