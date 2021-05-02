package me.tl0x.main.modules;


import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;

public class SpeedMine extends Module {
    public SpeedMine() {super("Speedmine","Makes you mine fast");}
    int maxDistance = 5;
    public static int speed = 1;
    public boolean enabled = false;

    @Override
    public void onEnable() {
        enabled = true;
        super.onEnable();
    }

    @Override
    public void OnTick() {
        if (enabled) {
            if (MinecraftClient.getInstance().player.isUsingItem()) return;
            if (MinecraftClient.getInstance().options.keyAttack.isPressed()) {
                HitResult hitResult = MinecraftClient.getInstance().player.raycast(maxDistance, 1f / 20f, false);
                if (hitResult.getType() == HitResult.Type.ENTITY && MinecraftClient.getInstance().player.interact(((EntityHitResult) hitResult).getEntity(), Hand.MAIN_HAND) != ActionResult.PASS)
                    return;
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockPos pos = ((BlockHitResult) hitResult).getBlockPos();
                    Direction side = ((BlockHitResult) hitResult).getSide();
                    assert MinecraftClient.getInstance().interactionManager != null;
                    for (int i = 0; i < speed; i++) {
                        MinecraftClient.getInstance().interactionManager.updateBlockBreakingProgress(pos, Direction.UP);
                        MinecraftClient.getInstance().player.swingHand(Hand.MAIN_HAND);
                    }


                }
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
