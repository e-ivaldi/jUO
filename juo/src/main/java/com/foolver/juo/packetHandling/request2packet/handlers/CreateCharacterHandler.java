package com.foolver.juo.packetHandling.request2packet.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.CreateCharacterPacket;
import com.foolver.juo.packetHandling.packets.utils.Skill;
import com.foolver.juo.util.DataReader;

public class CreateCharacterHandler extends AbstractRequestHandler<CreateCharacterPacket> {

  private static final Logger log = LoggerFactory.getLogger(CreateCharacterHandler.class);
  
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
      Skill skill1 = Skill.fromValue(dataReader.readByte());
      byte skill1Value = dataReader.readByte();
      Skill skill2 = Skill.fromValue(dataReader.readByte());
      byte skill2Value = dataReader.readByte();
      Skill skill3 = Skill.fromValue(dataReader.readByte());
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
      log.info(String.format("skill1: %s, value: %s", skill1, skill1Value));
      log.info(String.format("skill2: %s, value: %s", skill2, skill2Value));
      log.info(String.format("skill3: %s, value: %s ", skill3, skill3Value));
      return new CreateCharacterPacket(
          charName,
          skill1, skill1Value,
          skill2, skill2Value,
          skill3, skill3Value,
          skinColor);
      });

  }

}
