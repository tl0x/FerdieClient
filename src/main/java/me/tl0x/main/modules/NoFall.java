package me.tl0x.main.modules;

import me.tl0x.main.etc.based.Module;
import net.minecraft.client.MinecraftClient;

public class NoFall extends Module{
    public NoFall() {
        super("NoFall", "Allows you to take no fall damage");
    }
    public boolean enabled = false;
    double falldistance = 5;

    @Override
    public void onEnable() {
        enabled = true;
        super.onEnable();
    }

    @Override
    public void OnTick() {
        if (enabled) {
            if (MinecraftClient.getInstance().player.fallDistance > falldistance){
                MinecraftClient.getInstance().player.fallDistance = 0;
                MinecraftClient.getInstance().player.setVelocity(0,0.1,0);
            }
        }
        super.OnTick();
    }

    @Override
    public void onDisable() {
        enabled = false;
        super.onDisable();
    }
}
