package com.foolver.juo.packetHandling.packets.shared;

import com.foolver.juo.packetHandling.packets.response.AbstractResponsePacket;

public class CharacterMoveACKPacket extends AbstractResponsePacket {

  private byte movementSequenceKey;
  private byte notorietyFlag = 0x01;

  public CharacterMoveACKPacket(byte movementSequenceKey) {
    this(movementSequenceKey, (byte) 0x01);
  }

  public CharacterMoveACKPacket(byte movementSequenceKey, byte notorietyFlag) {
    super(false);
    this.movementSequenceKey = movementSequenceKey;
    this.notorietyFlag = notorietyFlag;
    allocateAndSetupBuffer();
  }

  @Override
  protected void fillBuffer() {
    buffer.put((byte) 0x22); // packetId
    buffer.put(movementSequenceKey);
    buffer.put(notorietyFlag); // notorietyFlag
    /*
     * Notoriety 0 = invalid/across server line 1 = innocent (blue) 2 =
     * guilded/ally (green) 3 = attackable but not criminal (gray) 4 = criminal
     * (gray) 5 = enemy (orange) 6 = murderer (red) 7 = unknown use (translucent
     * (like 0x4000 hue))
     */
  }

  @Override
  public int getBufferSize() {
    return 3;
  }

  public byte getMovementSequenceKey() {
    return movementSequenceKey;
  }

  public byte getNotorietyFlag() {
    return notorietyFlag;
  }

}
