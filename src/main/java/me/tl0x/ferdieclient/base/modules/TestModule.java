package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Keybind;
import me.tl0x.ferdieclient.base.Module;

import me.tl0x.ferdieclient.helpers.helper;
import me.tl0x.ferdieclient.helpers.render.Renderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.apache.logging.log4j.Level;


public class TestModule extends Module {


    public TestModule() {
        super("Test","If you see this, I probably forgot to remove it.");
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onTick() {
//        HitResult hr = FerdieClient.client.crosshairTarget;
//        Vec3d pos = new Vec3d(0,-1,0);
//        if (hr instanceof BlockHitResult bhr) {
//            BlockPos block = bhr.getBlockPos();
//        }
//        if (pos != null) {
//            Renderer.renderBlockOutline(pos, new Vec3d(1,1,1), 0,0,0, 255);
//        }
//        super.onTick();
    }

    @Override
    public void onRender(MatrixStack matrices, float tickDelta) {
        Vec3d pos = FerdieClient.client.player.getPos();
        Renderer.renderBlockOutline(pos.subtract(0,-1,0), new Vec3d(1,1,1), 50,255,173,255);
        FerdieClient.log(Level.INFO, "onRender called!");
        super.onRender(matrices, tickDelta);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
