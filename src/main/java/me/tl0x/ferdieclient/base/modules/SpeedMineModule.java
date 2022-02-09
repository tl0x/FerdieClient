package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Module;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;

public class SpeedMineModule extends Module {

    public int speed = 2;

    public SpeedMineModule() {
        super("Speedmine", "Makes you mine fast using packets");
    }

    @Override
    public void onTick() {
        HitResult hitResult = FerdieClient.client.crosshairTarget;
        if (hitResult instanceof BlockHitResult blockHitResult) {
            if (FerdieClient.client.options.keyAttack.isPressed()) {
                for(int i = 0; i < speed; i++) {
                    FerdieClient.client.interactionManager.updateBlockBreakingProgress(blockHitResult.getBlockPos(), Direction.UP);
                    FerdieClient.client.player.swingHand(Hand.MAIN_HAND);
                }
            }
        }
        super.onTick();
    }
}
