package me.tl0x.ferdieclient.helpers;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class helper {

    public static void sendMessage(String msg) {
        MinecraftClient.getInstance().player.sendMessage(Text.of(Formatting.AQUA + "[" + Formatting.RED + "FerdieClient" + Formatting.AQUA + "] " + Formatting.RESET + msg),false);
    }
}
