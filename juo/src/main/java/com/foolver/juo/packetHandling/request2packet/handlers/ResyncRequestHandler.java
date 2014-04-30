package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.shared.CharacterMoveACKPacket;
import com.foolver.juo.util.DataReader;

public class ResyncRequestHandler extends AbstractRequestHandler<CharacterMoveACKPacket> {

  @Override
  public CharacterMoveACKPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      byte sequence = dataReader.readByte();
      byte notorietyFlag = dataReader.readByte();
      return new CharacterMoveACKPacket(sequence, notorietyFlag);
    });
  }
}
