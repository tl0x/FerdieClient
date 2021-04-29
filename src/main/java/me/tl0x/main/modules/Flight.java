package me.tl0x.main.modules;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Module;
import net.minecraft.client.MinecraftClient;


public class Flight extends Module {
    public Flight() {
        super("Flight","Allows you to fly");
    }


    @Override
    public void OnTick() {
        MinecraftClient.getInstance().player.abilities.flying = true;
        super.OnTick();
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        MinecraftClient.getInstance().player.abilities.flying = false;
        super.onDisable();
    }
}
