package me.tl0x.ferdieclient.modules;

import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.Gui.TestGui;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.PacketEvent;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;

import java.util.Locale;

public class TestModule extends Module {
    int i = 0;
    public TestModule() {
        super("Test","If you see this, I probably forgot to remove it.");
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onTick() {

        i++;
        if (i>20) {
            i = 0;
            MinecraftClient.getInstance().setScreen(new TestGui());
            this.toggle();
        }
        super.onTick();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
