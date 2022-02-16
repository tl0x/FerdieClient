package me.tl0x.ferdieclient.helpers;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import me.tl0x.ferdieclient.FerdieClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.ShulkerBoxScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.ShulkerBoxScreenHandler;
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
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void quickMove(int slot) {
        if (FerdieClient.client.player.currentScreenHandler instanceof ShulkerBoxScreenHandler) {
            ShulkerBoxScreenHandler sh = (ShulkerBoxScreenHandler) FerdieClient.client.player.currentScreenHandler;
            Int2ObjectArrayMap<ItemStack> stack = new Int2ObjectArrayMap();
            stack.put(slot, sh.getSlot(slot).getStack());
            FerdieClient.client.getNetworkHandler().sendPacket((Packet) new ClickSlotC2SPacket(sh.syncId, 0, slot, 0, SlotActionType.QUICK_MOVE, sh.getSlot(slot).getStack(), (Int2ObjectMap) stack));
        }
    }
    public static void quickMoveAll() {
        for(int i = 0; i < 27; i++) {
            quickMove(i);
        }
    }
}
