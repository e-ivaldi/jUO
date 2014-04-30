package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.util.DataReader;

public class RequestSkillHandler extends AbstractRequestHandler<EmptyPacket> {

  @Override
  public EmptyPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      // TODO: implement this properly
        short blockSize = dataReader.readShort();
        byte type = dataReader.readByte();
        dataReader.skip(blockSize - 3);
        return new EmptyPacket();
      });

  }

}
