package me.tl0x.main.modules;

import me.tl0x.main.etc.based.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class Freeze extends Module {
    Vec3d currPos;
    public Freeze() {
        super("Freeze","Freezes you in place");
    }
    @Override
    public void onEnable() {
        currPos = MinecraftClient.getInstance().player.getPos();
        super.onEnable();
    }
    @Override
    public void OnTick() {
        MinecraftClient.getInstance().player.updatePosition(currPos.x,currPos.y,currPos.z);
        super.OnTick();
    }

}
