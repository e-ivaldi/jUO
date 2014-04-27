package com.foolver.juo.packetHandling.exception;

@SuppressWarnings("serial")
public class PacketHandlingException extends Exception {

  public PacketHandlingException(String msg) {
    super(msg);
  }

  public PacketHandlingException(String msg, Exception e) {
    super(msg, e);
  }

}
