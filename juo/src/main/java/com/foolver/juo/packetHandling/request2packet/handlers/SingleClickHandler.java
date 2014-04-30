package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.util.DataReader;

public class SingleClickHandler extends AbstractRequestHandler<EmptyPacket> {

  @Override
  public EmptyPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      // TODO: this is completely wrong, fix it
        dataReader.skip(4);
        return new EmptyPacket();
      });
  }

}
