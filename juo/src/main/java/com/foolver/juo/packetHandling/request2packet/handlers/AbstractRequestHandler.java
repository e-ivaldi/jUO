package com.foolver.juo.packetHandling.request2packet.handlers;

import java.io.IOException;
import java.io.InputStream;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.Packet;

public abstract class AbstractRequestHandler<P extends Packet> implements RequestHandler<P> {

  protected String getFixedSizeStringFromInputStream(InputStream is, int size) throws IOException {
    byte[] buf = readBytesFromInputStream(is, size);
    return new String(buf).trim().replaceAll(Character.toString('\0'),"");
  }

  protected byte getByteFromInputStream(InputStream is) throws IOException {
    return readBytesFromInputStream(is, 1)[0];
  }

  protected short getShortFromInputStream(InputStream is) throws IOException {
    byte[] bytes = readBytesFromInputStream(is, 2);
    return (short) ((bytes[0] << 8) | bytes[1]);
  }

  protected int getIntFromInputStream(InputStream is) throws IOException {
    byte[] bytes = readBytesFromInputStream(is, 4);
    return (int) (bytes[0] << 24 | bytes[1] << 16 | bytes[2] << 8 | bytes[3]);
  }
  
  protected void skypBytes(InputStream is, long n) throws IOException{
    is.skip(n);
  }

  private byte[] readBytesFromInputStream(InputStream is, int size) throws IOException {
    byte[] buf = new byte[size];
    is.read(buf, 0, size);
    return buf;
  }
  
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
