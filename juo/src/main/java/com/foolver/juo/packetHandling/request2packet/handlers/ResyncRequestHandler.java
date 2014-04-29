package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.shared.CharacterMoveACKPacket;

public class ResyncRequestHandler extends AbstractRequestHandler<CharacterMoveACKPacket>{

  @Override
  public CharacterMoveACKPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      byte sequence = getByteFromInputStream(is);
      byte notorietyFlag = getByteFromInputStream(is);
      return new CharacterMoveACKPacket(sequence, notorietyFlag);
    });
  }
}
