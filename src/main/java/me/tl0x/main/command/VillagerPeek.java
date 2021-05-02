package me.tl0x.main.command;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.village.TradeOfferList;

import javax.security.auth.callback.CallbackHandler;

public class VillagerPeek extends Command {
    public VillagerPeek() {
        super("VillagerPeek","Views villager trades before you open the gui",new String[]{});
    }

    @Override
    public void onExecute(String[] args) {
        assert MinecraftClient.getInstance().player != null;
        if (MinecraftClient.getInstance().targetedEntity instanceof VillagerEntity){
            VillagerEntity entity = (VillagerEntity) MinecraftClient.getInstance().targetedEntity;
            TradeOfferList trades = entity.getOffers();
            for(int i = 0; i < trades.size(); i++) {
                CHelper.sendMessage(trades.get(i).getSellItem().toString());
            }
        }
        else {
            CHelper.sendMessage("Please target a villager with your crosshair!");
            return;
        }
        super.onExecute(args);
    }
}
