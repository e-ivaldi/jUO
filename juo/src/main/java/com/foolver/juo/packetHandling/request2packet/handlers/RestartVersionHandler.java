package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.RestartVersionPacket;
import com.foolver.juo.util.DataReader;

public class RestartVersionHandler extends AbstractRequestHandler<RestartVersionPacket> {

  @Override
  public RestartVersionPacket handle(DataReader dataReader) throws PacketHandlingException {
    try {
      // TODO implement this
      dataReader.skip(1);
    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }
    return new RestartVersionPacket();
  }

}
