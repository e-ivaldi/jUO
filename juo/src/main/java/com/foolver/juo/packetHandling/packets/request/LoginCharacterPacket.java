package com.foolver.juo.packetHandling.packets.request;

public class LoginCharacterPacket extends AbstractRequestPacket {

  private String characterName;
  private int slotChosen;
  private int clientFlag;

  public LoginCharacterPacket(String characterName, int slotChosen, int clientFlag) {
    super();
    this.characterName = characterName;
    this.slotChosen = slotChosen;
    this.clientFlag = clientFlag;
  }

  public String getCharacterName() {
    return characterName;
  }

  public int getSlotChosen() {
    return slotChosen;
  }

  public int getClientFlag() {
    return clientFlag;
  }

}
