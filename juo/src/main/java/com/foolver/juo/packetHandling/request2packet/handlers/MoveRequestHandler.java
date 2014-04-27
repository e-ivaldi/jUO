package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.MoveRequestPacket;
import com.foolver.juo.packetHandling.packets.utils.Direction;

public class MoveRequestHandler extends AbstractRequestHandler<MoveRequestPacket> {

  private static final Logger log = LoggerFactory.getLogger(MoveRequestHandler.class);

  @Override
  public MoveRequestPacket handle(InputStream is) throws PacketHandlingException {
    return this.execute(() -> {
      Direction direction = Direction.fromValue(getByteFromInputStream(is));
      byte sequenceNumber = getByteFromInputStream(is);
      log.info(String.format("move sequence arrived: %s", sequenceNumber));
      int fastWalkPreventionKey = getIntFromInputStream(is);

      return new MoveRequestPacket(direction, sequenceNumber, fastWalkPreventionKey);
    });
  }
}
