package me.tl0x.ferdieclient.base.commands;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.AirBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;


import java.util.ArrayList;
import java.util.List;

public class TestCommand extends Command {

    public TestCommand() {
        super("Test","If you see this, I probably forgot to remove it.",new String[]{"alias1"});
    }

    @Override
    public void onExecute(String[] args) {
//        PlayerInventory inv = FerdieClient.client.player.getInventory();
//        List<ItemStack> items = inv.main;
//
//        for (ItemStack i: items) {
//            if (!(i.getItem() instanceof AirBlockItem)) {
//                helper.sendMessage(i.getItem().getName().getString() + " x" + Integer.toString(i.getCount()));
//            }
//        }

        PlayerEntity target = null;
        HitResult hr = FerdieClient.client.crosshairTarget;
        if (hr instanceof EntityHitResult ehr && ehr.getEntity() instanceof PlayerEntity) {
            target = (PlayerEntity) ehr.getEntity();
        }

        if (target != null) {
            List<ItemStack> inv = target.getInventory().main;
            for(ItemStack i: inv) {
                if (!i.isEmpty()) {
                    helper.sendMessage(i.getItem().getName().getString() + " x" + Integer.toString(i.getCount()));
                }
            }

        }
    }
}
