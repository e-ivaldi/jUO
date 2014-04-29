package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.RequestPlayerStatusPacket;

public class RequestPlayerStatusHandler extends AbstractRequestHandler<RequestPlayerStatusPacket> {

  @Override
  public RequestPlayerStatusPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
        skypBytes(is, 4); // unknown
        byte type = getByteFromInputStream(is);
        int serial = getIntFromInputStream(is);
        return new RequestPlayerStatusPacket(serial, type);
      });

  }

}
