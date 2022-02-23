package me.tl0x.ferdieclient.base.modules.util;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.bases.Module;
import me.tl0x.ferdieclient.base.modules.ModuleType;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.PacketEvent;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

public class MuteChatModule extends Module {

    public MuteChatModule() {
        super("MuteChat", "Mutes the chat");
        EventHandler.registerEventHandler(EventType.PACKET_RECIEVE, event -> {
            if(!this.isEnabled) return;
            PacketEvent e = (PacketEvent) event;
            if (FerdieClient.client.player != null && e.getPacket() instanceof GameMessageS2CPacket) {
                event.setCancelled(true);
            }
        });
        setModuleType(ModuleType.MODULETYPE_UTIL);
    }


}
