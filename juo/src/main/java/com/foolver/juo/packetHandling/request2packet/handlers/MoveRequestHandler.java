package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.MoveRequestPacket;

public class MoveRequestHandler extends AbstractRequestHandler<MoveRequestPacket> {

  @Override
  public MoveRequestPacket handle(InputStream is) throws PacketHandlingException {
    
    byte direction;
    byte sequenceNumber;
    int fastWalkPreventionKey;
    
    try {
      direction = getByteFromInputStream(is);
      sequenceNumber = getByteFromInputStream(is);
      fastWalkPreventionKey = getIntFromInputStream(is);
    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }
    
    return new MoveRequestPacket(direction, sequenceNumber, fastWalkPreventionKey);
  }
}
