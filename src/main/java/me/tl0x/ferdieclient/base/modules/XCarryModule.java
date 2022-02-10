package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Keybind;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.PacketEvent;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.network.packet.c2s.play.CloseHandledScreenC2SPacket;

public class XCarryModule extends Module {

    public boolean invOpened = false;

    public XCarryModule() {
        super("XCarry", "Allows you to use crafting table as slots for inventory", new Keybind(-1));
        EventHandler.registerEventHandler(EventType.PACKET_SEND, event -> {
            if (!this.isEnabled) {
                return;
            }
            PacketEvent e = (PacketEvent) event;
            if (FerdieClient.client.player != null) {
                if (e.getPacket() instanceof CloseHandledScreenC2SPacket) {
                    if (((CloseHandledScreenC2SPacket) e.getPacket()).getSyncId() == FerdieClient.client.player.currentScreenHandler.syncId) {
                        e.setCancelled(true);
                        invOpened = true;
                    }
                }
            }
        });
    }

    @Override
    public void onDisable() {
        if (invOpened) {
            FerdieClient.client.getNetworkHandler().sendPacket(new CloseHandledScreenC2SPacket(FerdieClient.client.player.currentScreenHandler.syncId));
        }
        super.onDisable();
    }

    @Override
    public void onEnable() {
        invOpened = false;
        super.onEnable();
    }
}
