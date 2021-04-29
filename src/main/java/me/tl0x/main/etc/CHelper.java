package me.tl0x.main.etc;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class CHelper {
    public static void sendMessage(String msg) {
        MinecraftClient.getInstance().player.sendMessage(Text.of(msg), false);
    }
}
