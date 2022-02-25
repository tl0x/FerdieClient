package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.bases.Module;

import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.render.Renderer;
import net.minecraft.block.AirBlock;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;

import java.awt.*;


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

    }

    @Override
    public void onRender(MatrixStack matrices, float tickDelta) {


        super.onTick();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
