package me.tl0x.ferdieclient.base.commands;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;

import java.util.ArrayList;

public class TestCommand extends Command {

    public TestCommand() {
        super("Test","If you see this, I probably forgot to remove it.",new String[]{"alias1"});
    }

    @Override
    public void onExecute(String[] args) {
        HitResult e = FerdieClient.client.crosshairTarget;
        VillagerEntity ve = null;
        if (e instanceof EntityHitResult ehr && ehr.getEntity() instanceof VillagerEntity) {
            ve = (VillagerEntity) ehr.getEntity();
        }


        if (ve != null) {
            ArrayList<TradeOffer> trades = ve.getOffers();
            for (TradeOffer t: trades) {
                helper.sendMessage(t.getAdjustedFirstBuyItem().getName().toString());
                helper.sendMessage(t.getSellItem().getName().toString());
            }
        }

    }
}
