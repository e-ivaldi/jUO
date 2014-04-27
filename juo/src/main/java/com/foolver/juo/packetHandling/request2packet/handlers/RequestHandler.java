package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.Packet;

public interface RequestHandler<P extends Packet> {

  P handle(InputStream is) throws PacketHandlingException;

}
