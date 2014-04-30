package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.DeleteCharacterPacket;
import com.foolver.juo.util.DataReader;

public class DeleteCharacterHandler extends AbstractRequestHandler<DeleteCharacterPacket> {

  @Override
  public DeleteCharacterPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      String password = dataReader.readString(30);
      int charIndex = dataReader.readInt();
      int clientIp = dataReader.readInt();
      return new DeleteCharacterPacket(password, charIndex, clientIp);
    });

  }

}
