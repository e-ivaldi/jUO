package com.foolver.juo.packetHandling.packets.request;

public class SpeechRequestPacket extends AbstractRequestPacket {

  private String text;

  public SpeechRequestPacket(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

}
