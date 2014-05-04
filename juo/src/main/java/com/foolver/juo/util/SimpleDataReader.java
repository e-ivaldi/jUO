package com.foolver.juo.util;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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
    String s = new String(buf, calculateEncoding(buf[0]));
    int endOffset = s.indexOf('\0');
    if (endOffset != -1) {
      s = s.substring(0, endOffset);
    }
    return s;
  }

  private String calculateEncoding(byte b) {
    if (b == '\0') {
      return StandardCharsets.UTF_16.displayName();
    } else {
      return StandardCharsets.UTF_8.displayName();
    }
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
