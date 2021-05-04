package me.tl0x.main.modules;


import me.tl0x.main.etc.based.Keybind;
import me.tl0x.main.etc.based.Module;
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

public class ClickTP extends Module {
    public boolean enabled = false;
    int maxDistance = 5;
    public ClickTP() {
        super("ClickTP", "TPs to where you right click");
    }

    @Override
    public void onEnable() {
        enabled = true;
        super.onEnable();
    }

    @Override
    public void OnTick() {
        if (enabled) {
            if (MinecraftClient.getInstance().player.isUsingItem()) return;
            if (MinecraftClient.getInstance().options.keyUse.isPressed()) {
                HitResult hitResult = MinecraftClient.getInstance().player.raycast(maxDistance, 1f / 20f, false);
                if (hitResult.getType() == HitResult.Type.ENTITY && MinecraftClient.getInstance().player.interact(((EntityHitResult) hitResult).getEntity(), Hand.MAIN_HAND) != ActionResult.PASS)
                    return;
                if (hitResult.getType() == HitResult.Type.BLOCK) {
                    BlockPos pos = ((BlockHitResult) hitResult).getBlockPos();
                    Direction side = ((BlockHitResult) hitResult).getSide();

                    if (MinecraftClient.getInstance().world.getBlockState(pos).onUse(MinecraftClient.getInstance().world, MinecraftClient.getInstance().player, Hand.MAIN_HAND, (BlockHitResult) hitResult) != ActionResult.PASS)
                        return;

                    BlockState state = MinecraftClient.getInstance().world.getBlockState(pos);

                    VoxelShape shape = state.getCollisionShape(MinecraftClient.getInstance().world, pos);
                    if (shape.isEmpty()) shape = state.getOutlineShape(MinecraftClient.getInstance().world, pos);

                    double height = shape.isEmpty() ? 1 : shape.getMax(Direction.Axis.Y);

                    MinecraftClient.getInstance().player.updatePosition(pos.getX() + 0.5 + side.getOffsetX(), pos.getY() + height, pos.getZ() + 0.5 + side.getOffsetZ());


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
