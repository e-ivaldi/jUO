package com.foolver.juo.engine;

import java.util.ArrayList;
import java.util.List;

import com.foolver.juo.ClientHandler;
import com.foolver.juo.game.PlayerInfo;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.response.DrawEquippedMob;
import com.foolver.juo.packetHandling.packets.special.MultiPacket;

public class Notificator {

  private List<ClientHandler> handlers = new ArrayList<>();

  public void onMove(PlayerInfo mover) {
    Packet packet = new MultiPacket(new DrawEquippedMob(mover));    
    // to use the parallel stream I should guarantee
    // that the collection is not modified during the logic:
    // we'll see if it worths th lock the list up
    handlers.stream().filter(h->!h.getPlayerInfo().equals(mover)).forEach(
        h->h.addToResponseQueue(packet));  
  }

  // TODO add a method to unregister the handler and call it from the handler
  // itself

  public void registerClientHandler(ClientHandler clientHandler) {
    handlers.add(clientHandler);
  }

}
