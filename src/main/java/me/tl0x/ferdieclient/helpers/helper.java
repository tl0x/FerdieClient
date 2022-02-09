package me.tl0x.ferdieclient.helpers;

import me.tl0x.ferdieclient.FerdieClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.Objects;

public class helper {

    public static void sendMessage(String msg) {
        FerdieClient.client.player.sendMessage(Text.of(Formatting.AQUA + "[" + Formatting.RED + "FerdieClient" + Formatting.AQUA + "] " + Formatting.RESET + msg),false);
    }

    public static int fixSlot(int slotId) {
        if (slotId < 9) {
            return slotId + 36;
        }
        return slotId;
    }

    public static void swapSlots(int slotId, int targetSlot) {
        int fixedSlot = fixSlot(slotId);
        Objects.requireNonNull(FerdieClient.client.interactionManager).clickSlot(0,slotId,0, SlotActionType.PICKUP, FerdieClient.client.player);
        FerdieClient.client.interactionManager.clickSlot(0,targetSlot,0,SlotActionType.PICKUP, FerdieClient.client.player);
        FerdieClient.client.interactionManager.clickSlot(0, slotId, 0, SlotActionType.PICKUP, FerdieClient.client.player);
        helper.sendMessage(Integer.toString(slotId) + " " + Integer.toString(targetSlot));
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
