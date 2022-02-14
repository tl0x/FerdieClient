package me.tl0x.ferdieclient.base.modules.render;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Module;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class FullBrightModule extends Module {

    double normGamma;

    public FullBrightModule() {
        super("FullBright", "Makes everything bright");
    }

    @Override
    public void onEnable() {
        normGamma = FerdieClient.client.options.gamma;
        FerdieClient.client.options.gamma = 16;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        FerdieClient.client.options.gamma = normGamma;
        super.onDisable();
    }
}
