package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.util.DataReader;

public class BooksHandler extends AbstractRequestHandler<EmptyPacket> {

  @Override
  public EmptyPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      short packetLength = dataReader.readShort();
      dataReader.skip(packetLength - 3); // type
        return new EmptyPacket();
      });

  }
}
