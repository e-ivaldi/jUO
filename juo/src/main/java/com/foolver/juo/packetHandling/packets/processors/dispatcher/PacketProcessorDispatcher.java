package com.foolver.juo.packetHandling.packets.processors.dispatcher;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foolver.juo.packetHandling.exception.PacketHandlingException;
import com.foolver.juo.packetHandling.packets.Packet;
import com.foolver.juo.packetHandling.packets.processors.DeleteCharacterPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.EmptyPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.GameServerLoginPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.GraphicalEffectPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.LoginCharacterPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.LoginRequestPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.MoveRequestPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.PacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.RestartVersionPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.SelectServerPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.ServerListRemoveEntryPacketProcessor;
import com.foolver.juo.packetHandling.packets.processors.SpeechRequestPacketProcessor;
import com.foolver.juo.packetHandling.packets.request.DeleteCharacterPacket;
import com.foolver.juo.packetHandling.packets.request.GameServerLoginPacket;
import com.foolver.juo.packetHandling.packets.request.GraphicalEffectPacket;
import com.foolver.juo.packetHandling.packets.request.LoginCharacterPacket;
import com.foolver.juo.packetHandling.packets.request.LoginRequestPacket;
import com.foolver.juo.packetHandling.packets.request.MoveRequestPacket;
import com.foolver.juo.packetHandling.packets.request.RestartVersionPacket;
import com.foolver.juo.packetHandling.packets.request.SelectServerPacket;
import com.foolver.juo.packetHandling.packets.request.ServerListRemoveEntryPacket;
import com.foolver.juo.packetHandling.packets.request.SpeechRequestPacket;
import com.foolver.juo.packetHandling.packets.response.EmptyPacket;

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
  }

  @SuppressWarnings("rawtypes")
  public PacketProcessor getPacketProcessor(Packet packet) throws PacketHandlingException {
    PacketProcessor<? extends Packet> packetProcessor = packetProcessors.get(packet.getClass());
    if (packetProcessor == null) {
      throw new PacketHandlingException(String.format("unable to find a processor for the packet %s", packet.getClass()
          .getSimpleName()));
    }
    logPacketProcessorInfo(packet, packetProcessor);
    return packetProcessor;
  }

  private void logPacketProcessorInfo(Packet packet, PacketProcessor<? extends Packet> packetProcessor) {
    log.info(String.format("using packetProcessor %s for packet %s", packetProcessor.getClass().getSimpleName(), packet
        .getClass().getSimpleName()));
  }

}
