package com.foolver.juo.packetHandling.request2packet.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.MoveRequestPacket;
import com.foolver.juo.packetHandling.packets.utils.Direction;
import com.foolver.juo.util.DataReader;

public class MoveRequestHandler extends AbstractRequestHandler<MoveRequestPacket> {

  private static final Logger log = LoggerFactory.getLogger(MoveRequestHandler.class);

  @Override
  public MoveRequestPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      Direction direction = Direction.fromValue(dataReader.readByte());
      byte sequenceNumber = dataReader.readByte();
      log.info(String.format("move sequence arrived: %s, direction: %s", sequenceNumber, direction));
      int fastWalkPreventionKey = dataReader.readInt();
      return new MoveRequestPacket(direction, sequenceNumber, fastWalkPreventionKey);
    });
  }
}
