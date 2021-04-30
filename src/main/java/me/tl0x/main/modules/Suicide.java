package me.tl0x.main.modules;

import me.tl0x.main.etc.based.Module;
import net.minecraft.client.MinecraftClient;

public class Suicide extends Module {
    public Suicide() {
        super("Suicide","When the trolling is immense");
    }

    @Override
    public void OnTick() {
        new Thread(() -> {
            try {
                assert MinecraftClient.getInstance().player != null;
                MinecraftClient.getInstance().player.setVelocity(0,5,0);
                Thread.sleep(500);
                MinecraftClient.getInstance().player.setVelocity(0,-10,0);
            } catch (Exception ignored) {

            }

        }).start();
        super.OnTick();
    }
}
