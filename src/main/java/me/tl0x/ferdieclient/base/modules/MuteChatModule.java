package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Keybind;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.PacketEvent;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class MuteChatModule extends Module {

    public MuteChatModule() {
        super("MuteChat", "Mutes the chat", new Keybind(-1));
        EventHandler.registerEventHandler(EventType.PACKET_RECIEVE, event -> {
            if(!this.isEnabled) return;
            PacketEvent e = (PacketEvent) event;
            if (FerdieClient.client.player != null && e.getPacket() instanceof ChatMessageC2SPacket) {
                event.setCancelled(true);
            }
        });
    }


}
