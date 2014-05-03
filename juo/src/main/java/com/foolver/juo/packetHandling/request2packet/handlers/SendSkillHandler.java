package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.SendSkillPacket;
import com.foolver.juo.util.DataReader;

public class SendSkillHandler extends AbstractRequestHandler<SendSkillPacket> {

  @Override
  public SendSkillPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
        dataReader.skipShort(); // packet length
        short skillId = dataReader.readShort();
        byte skillLockState = dataReader.readByte();
        return new SendSkillPacket(skillId, skillLockState);
      });

  }
}
