package com.foolver.juo.packetHandling.request2packet.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.request.SingleClickPacket;
import com.foolver.juo.util.DataReader;

public class SingleClickHandler extends AbstractRequestHandler<SingleClickPacket> {

  private static final Logger log = LoggerFactory.getLogger(SingleClickHandler.class);
  
  @Override
  public SingleClickPacket handle(DataReader dataReader) throws PacketHandlingException {
    return this.execute(() -> {
      int objectId = dataReader.readInt();
      log.info(String.format("user single clicked on object id: %s", objectId));
      return new SingleClickPacket(objectId);
    });
  }

}
