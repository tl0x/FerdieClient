package me.tl0x.ferdieclient.base.commands;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.bases.Command;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.realms.dto.PlayerInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.scanner.NbtCollector;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.apache.logging.log4j.Level;


import java.util.List;

public class TestCommand extends Command {

    public TestCommand() {
        super("Test","If you see this, I probably forgot to remove it.",new String[]{"alias1"});
    }

    @Override
    public void onExecute(String[] args) {
        PlayerEntity p = null;
        HitResult hr = FerdieClient.client.crosshairTarget;
        if (hr instanceof EntityHitResult ehr && ehr.getEntity() instanceof PlayerEntity) {
            p = (PlayerEntity) ehr.getEntity();
        } else {
            helper.sendMessage("hover over player or smth");
            return;
        }

        NbtCompound nbtCompound = new NbtCompound();
        p.writeCustomDataToNbt(nbtCompound);

        NbtList inventory = (NbtList) nbtCompound.get("Inventory");
        List<NbtElement> inv = (List<NbtElement>) inventory;

        for (NbtElement e: inv) {
            NbtCompound as = (NbtCompound) e;
            for(String i: as.getKeys()) {
                FerdieClient.log(Level.INFO, as.get(i).asString());
            }
        }



    }
}
