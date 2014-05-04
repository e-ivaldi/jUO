package com.foolver.juo.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleDataReader implements DataReader {

  private DataInputStream dataInputStream;

  public SimpleDataReader(InputStream is) {
    dataInputStream = new java.io.DataInputStream(is);
  }

  @Override
  public byte readByte() throws IOException {
    return dataInputStream.readByte();
  }

  @Override
  public short readShort() throws IOException {
    return dataInputStream.readShort();
  }

  @Override
  public int readInt() throws IOException {
    return dataInputStream.readInt();
  }

  @Override
  public String readString(int length) throws IOException {
    byte[] buf = new byte[length];
    dataInputStream.readFully(buf, 0, length);
    String s = new String(buf);
    return s.substring(0, s.indexOf('\0'));
  }

  @Override
  public void skip(int length) throws IOException {
    dataInputStream.skip(length);
  }

  @Override
  public void skipByte() throws IOException {
    skip(Byte.BYTES);    
  }

  @Override
  public void skipShort() throws IOException {
    skip(Short.BYTES);    
  }

  @Override
  public void skipInt() throws IOException {
    skip(Integer.BYTES);    
  }

  @Override
  public boolean readBool() throws IOException {
    return dataInputStream.readBoolean();
  }

}
