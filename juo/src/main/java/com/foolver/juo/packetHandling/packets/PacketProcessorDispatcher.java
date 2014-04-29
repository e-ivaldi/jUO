package com.foolver.juo.packetHandling.packets;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.processors.ClientVersionPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.DeleteCharacterPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.EmptyPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.GameServerLoginPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.GraphicalEffectPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.LoginCharacterPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.LoginRequestPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.MoveRequestPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.PacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.PingMessagePacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.RequestPlayerStatusPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.RequestTipNoticePacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.RestartVersionPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.ResyncRequestProcessor;
import com.foolver.juo.packetHandling.packets.processors.SelectServerPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.ServerListRemoveEntryPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.SpeechRequestPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.UltimaMessengerPacketProcessor;
import com.foolver.juo.packetHandling.packets.request.DeleteCharacterPacket;
import com.foolver.juo.packetHandling.packets.request.GameServerLoginPacket;
import com.foolver.juo.packetHandling.packets.request.GraphicalEffectPacket;
import com.foolver.juo.packetHandling.packets.request.LoginCharacterPacket;
import com.foolver.juo.packetHandling.packets.request.LoginRequestPacket;
import com.foolver.juo.packetHandling.packets.request.MoveRequestPacket;
import com.foolver.juo.packetHandling.packets.request.RequestPlayerStatusPacket;
import com.foolver.juo.packetHandling.packets.request.RestartVersionPacket;
import com.foolver.juo.packetHandling.packets.request.SelectServerPacket;
import com.foolver.juo.packetHandling.packets.request.ServerListRemoveEntryPacket;
import com.foolver.juo.packetHandling.packets.request.SpeechRequestPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;
import com.foolver.juo.packetHandling.packets.response.UltimaMessengerPacket;
import com.foolver.juo.packetHandling.packets.shared.CharacterMoveACKPacket;
import com.foolver.juo.packetHandling.packets.shared.ClientVersionPacket;
import com.foolver.juo.packetHandling.packets.shared.PingMessagePacket;
import com.foolver.juo.packetHandling.packets.shared.RequestTipNoticePacket;

public class PacketProcessorDispatcher {

  private static final Logger log = LoggerFactory.getLogger(PacketProcessorDispatcher.class);
  private static final Map<Class<? extends Packet>, PacketProcessor<? extends Packet>> packetProcessors = new HashMap<>();

  public PacketProcessorDispatcher() {
    packetProcessors.put(EmptyPacket.class, new EmptyPacketProcessor());
    packetProcessors.put(LoginRequestPacket.class, new LoginRequestPacketProcessor());
    packetProcessors.put(RestartVersionPacket.class, new RestartVersionPacketProcessor());
    packetProcessors.put(ServerListRemoveEntryPacket.class, new ServerListRemoveEntryPacketProcessor());
    packetProcessors.put(SelectServerPacket.class, new SelectServerPacketProcessor());
    packetProcessors.put(GraphicalEffectPacket.class, new GraphicalEffectPacketProcessor());
    packetProcessors.put(GameServerLoginPacket.class, new GameServerLoginPacketProcessor());
    packetProcessors.put(LoginCharacterPacket.class, new LoginCharacterPacketProcessor());
    packetProcessors.put(DeleteCharacterPacket.class, new DeleteCharacterPacketProcessor());
    packetProcessors.put(MoveRequestPacket.class, new MoveRequestPacketProcessor());
    packetProcessors.put(SpeechRequestPacket.class, new SpeechRequestPacketProcessor());
    packetProcessors.put(PingMessagePacket.class, new PingMessagePacketProcessor());
    packetProcessors.put(RequestTipNoticePacket.class, new RequestTipNoticePacketProcessor());
    packetProcessors.put(UltimaMessengerPacket.class, new UltimaMessengerPacketProcessor());
    packetProcessors.put(ClientVersionPacket.class, new ClientVersionPacketProcessor());
    packetProcessors.put(RequestPlayerStatusPacket.class, new RequestPlayerStatusPacketProcessor());
    packetProcessors.put(CharacterMoveACKPacket.class, new ResyncRequestProcessor());
  }

  @SuppressWarnings("rawtypes")
  public PacketProcessor getPacketProcessor(Packet packet) throws PacketHandlingException {
    PacketProcessor<? extends Packet> packetProcessor = packetProcessors.get(packet.getClass());
    if (packetProcessor == null) {
      log.warn(String.format("processor not found for the packet %s, using the emptyPacketProcessor", packet.getClass()
          .getSimpleName()));
      packetProcessor = packetProcessors.get(EmptyPacket.class);
    }
    return packetProcessor;
  }
}
