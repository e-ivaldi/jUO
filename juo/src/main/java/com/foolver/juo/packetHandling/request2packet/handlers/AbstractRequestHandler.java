package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.Packet;

public abstract class AbstractRequestHandler<P extends Packet> implements RequestHandler<P> {

  protected P execute(StreamReader<P> streamReader) throws PacketHandlingException{
    try {
      return (P) streamReader.execute();
    } catch (IOException e) {
      throw new PacketHandlingException(String.format("unable to handler packet %s", this.getClass().getSimpleName()),
          e);
    }
  }

  @FunctionalInterface
  public interface StreamReader<P>{
    P execute() throws IOException;
  }

}
