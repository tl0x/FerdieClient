package me.tl0x.ferdieclient.base.modules.util;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.bases.Module;
import me.tl0x.ferdieclient.base.modules.ModuleType;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;

import java.util.List;

public class AutoElytraModule extends Module {


    public AutoElytraModule() {
        super("AutoElytra", "Automatically makes you wear an elytra when you jump. Equips chest when landing.");
        this.setModuleType(ModuleType.MODULETYPE_UTIL);
    }

    @Override
    public void onTick() {
        ItemStack elytra = null;
        ItemStack chestplate = null;
        PlayerInventory inventory = FerdieClient.client.player.getInventory();
        List<ItemStack> lstinv = (List) inventory.main;

        for (ItemStack i : lstinv) {
            if (i.getItem() == Items.ELYTRA) {
                elytra = i;
            }
            if (i.getItem() == Items.LEATHER_CHESTPLATE || i.getItem() == Items.IRON_CHESTPLATE || i.getItem() == Items.GOLDEN_CHESTPLATE || i.getItem() == Items.DIAMOND_CHESTPLATE || i.getItem() == Items.NETHERITE_CHESTPLATE) {
                chestplate = i;
            }
        }

        if (FerdieClient.client.player.fallDistance > 1) {
            if(elytra != null) {
                int slot = helper.fixSlot(inventory.getSlotWithStack(elytra));
                helper.swapSlots(slot, 6);
            }
        }

        if (FerdieClient.client.player.isOnGround() && FerdieClient.client.player.fallDistance < 1) {
            if (chestplate != null) {
                int slot = helper.fixSlot(inventory.getSlotWithStack(chestplate));
                helper.swapSlots(slot, 6);
            }
        }

        super.onTick();
    }
}
