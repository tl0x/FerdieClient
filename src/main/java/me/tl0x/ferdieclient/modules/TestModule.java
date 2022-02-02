package me.tl0x.ferdieclient.modules;

import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.PacketEvent;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

import java.util.Locale;

public class TestModule extends Module {

    String word = "hello";
    int count = 0;
    public TestModule() {
        super("Test","If you see this, I probably forgot to remove it.");
        EventHandler.registerEventHandler(EventType.PACKET_RECIEVE, event -> {
            if(!this.isEnabled) {
                return;
            }
            PacketEvent packet = (PacketEvent) event;
            if(packet.getPacket() instanceof net.minecraft.network.packet.s2c.play.GameMessageS2CPacket p) {
                String msg = p.getMessage().getString().toLowerCase();
                helper.sendMessage(msg);
            }
        });
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onTick() {
        super.onTick();
    }

    @Override
    public void onDisable() {
        helper.sendMessage(Integer.toString(count));
        super.onDisable();
    }

}
