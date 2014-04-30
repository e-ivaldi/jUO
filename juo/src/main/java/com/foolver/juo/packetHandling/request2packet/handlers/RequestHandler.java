package com.foolver.juo.packetHandling.request2packet.handlers;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.util.DataReader;

public interface RequestHandler<P extends Packet> {

  P handle(DataReader dataReader) throws PacketHandlingException;

}
