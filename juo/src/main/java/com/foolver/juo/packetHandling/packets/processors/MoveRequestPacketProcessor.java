package com.foolver.juo.packetHandling.packets.processors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.engine.Engine;
import com.foolver.juo.engine.map.MapReader;
import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.request.MoveRequestPacket;
import com.foolver.juo.packetHandling.packets.shared.CharacterMoveACKPacket;
import com.foolver.juo.packetHandling.packets.utils.Direction;

public class MoveRequestPacketProcessor implements PacketProcessor<MoveRequestPacket> {

  private static final Logger log = LoggerFactory.getLogger(MoveRequestPacketProcessor.class);
  
  private MapReader mapReader;
  
  public MoveRequestPacketProcessor(MapReader mapReader) {
    this.mapReader = mapReader;
  }
  
  @Override
  public Packet processPacket(MoveRequestPacket packet) {
    PlayerInfo playerInfo = PlayerInfo.getInstance();
    Direction requestedDirection = packet.getDirection();
    if(requestedDirection == playerInfo.getDir()){
      updatePlaerInfoPosition(playerInfo, requestedDirection);
      byte z = mapReader.getMapCoordinateInfo(playerInfo.getPosX(), playerInfo.getPosY());
      log.info(String.format("position: %s,%s,%s", playerInfo.getPosX(), playerInfo.getPosY(), z));
    }
    playerInfo.setDir(requestedDirection);
    return new CharacterMoveACKPacket(packet.getSequenceNumber());
  }

  private void updatePlaerInfoPosition(PlayerInfo playerInfo, Direction direction) {
    switch(direction){
    case NORTH:
        playerInfo.decrementY();
        break;
    case EAST:
        playerInfo.incrementX();
      break;
    case SOUTH:
        playerInfo.incrementY();
      break;
    case WEST:
        playerInfo.decrementX();
      break;
    case NORTH_EAST:
        playerInfo.incrementX();
        playerInfo.decrementY();
      break;
    case SOUTH_EAST:
        playerInfo.incrementX();
        playerInfo.incrementY();
      break;
    case SOUTH_WEST:
        playerInfo.decrementX();
        playerInfo.incrementY();
      break;
    case NORTH_WEST:
        playerInfo.decrementX();
        playerInfo.decrementY();
      break;
    default:
      //TODO throw an exception here)(?)
      log.error("wrong direction!!!");
      break;
        
    }
  }
}
