package me.tl0x.ferdieclient.base.modules.util;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.base.modules.ModuleType;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AutoElytraModule extends Module {


    public AutoElytraModule() {
        super("AutoElytra", "Automatically makes you wear an elytra when you jump. Equips chest when landing.");
        this.setModuleType(ModuleType.MODULETYPE_UTIL);
    }

    @Override
    public void onTick() {
        ItemStack elytra = null;
        Boolean elytrainEnv = false;
        PlayerInventory inventory = FerdieClient.client.player.getInventory();
        if(inventory.getArmorStack(3).isOf(Items.ELYTRA)) {
            return;
        }
        List<ItemStack> lstinv = (List) inventory.main;

        for (ItemStack i : lstinv) {
            if (i.getItem() == Items.ELYTRA) {
                elytrainEnv = true;
                elytra = i;
            }
        }

        if (FerdieClient.client.player.fallDistance > 5) {
            if(elytra != null) {
                int slot = helper.fixSlot(inventory.getSlotWithStack(elytra));
                helper.swapSlots(slot, 6);
            }
        }

        super.onTick();
    }
}
