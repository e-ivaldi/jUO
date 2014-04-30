package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.RequestPlayerStatusPacket;
import com.foolver.juo.util.DataReader;

public class RequestPlayerStatusHandler extends AbstractRequestHandler<RequestPlayerStatusPacket> {

  @Override
  public RequestPlayerStatusPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
        dataReader.skip(4); // unknown
        byte type = dataReader.readByte();
        int serial = dataReader.readInt();
        return new RequestPlayerStatusPacket(serial, type);
      });

  }

}
