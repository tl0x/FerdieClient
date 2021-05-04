package me.tl0x.main.etc;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class CHelper {
    public static void sendMessage(String msg) {
        MinecraftClient.getInstance().player.sendMessage(Text.of(Formatting.AQUA + "[" + Formatting.RED + "FerdieClient" + Formatting.AQUA + "] " + Formatting.RESET + msg), false);
    }
}
