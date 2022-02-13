package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.PacketEvent;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;

public class DupeModule extends Module {

    public boolean startDuping = false;

    public DupeModule() {
        super("Dupe", "Shulker dupe for 1.18. Only works on vanilla/fabric servers.");
    }

    @Override
    public void onTick() {
        if (startDuping) {
            HitResult hr = FerdieClient.client.crosshairTarget;
            if (hr instanceof BlockHitResult ehr && FerdieClient.client.world.getBlockState(ehr.getBlockPos()).getBlock() instanceof ShulkerBoxBlock) {
                FerdieClient.client.interactionManager.updateBlockBreakingProgress(ehr.getBlockPos(), Direction.DOWN);
            } else {
                startDuping = false;
                FerdieClient.client.player.closeHandledScreen();
            }
        }

        super.onTick();
    }
}
