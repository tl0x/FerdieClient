package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.bases.Module;

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
//        HitResult hr = FerdieClient.client.crosshairTarget;
//        BlockPos block = null;
//        if (hr instanceof BlockHitResult bhr) {
//            block = bhr.getBlockPos();
//        }
//        if (block != null) {
//            Renderer.renderOutline(new Vec3d(block.getX(), block.getY(), block.getZ()), new Vec3d(1,1,1), Color.CYAN,matrices);
//        }

        BlockPos pos = FerdieClient.client.player.getBlockPos();
        Position playerPos = FerdieClient.client.player.getPos();
        for(int x = (int) pos.getX()-50; x < (int) pos.getX()+50; x++) {
            for (int y = (int) pos.getY()-2; y < (int) pos.getY()+2; y++) {
                if (!(FerdieClient.client.world.getBlockState(new BlockPos(x,y,(int) pos.getZ())).getBlock() instanceof AirBlock)) {
                    Renderer.renderOutline(new Vec3d(x, y, (int) pos.getZ()), new Vec3d(1, 1, 1), Color.CYAN, matrices);
                }
            }
        }
        super.onTick();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
