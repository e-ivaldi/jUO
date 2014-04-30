package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.util.DataReader;

public class LoadAreaHandler extends AbstractRequestHandler<EmptyPacket> {

  @Override
  public EmptyPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      // TODO: check what kind of response this request needs
        dataReader.skip(12); // unknown
        return new EmptyPacket();
      });

  }

}
