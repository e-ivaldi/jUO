package com.foolver.juo.util;

public class ByteUtil {

  public static String getPrintable(byte b) {
    return String.format("%02X", b);
  }

  public static String getPrintable(byte[] bytes) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < bytes.length; i++) {
      buffer.append(String.format("%s ",getPrintable(bytes[i])));
    }
    return buffer.toString();
  }

  public static byte parseByteFromString(String byteLiteral) {
    return (byte) Integer.parseInt(byteLiteral, 16);
  }

  public static byte[] getPaddedBytesOfString(String s, int length) {
    return String.format("%1$-" + length + "s", s, length).getBytes();
  }
}
