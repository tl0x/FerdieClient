package me.tl0x.ferdieclient.mixin.control;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Locale;

@Mixin(Screen.class)
public class ChatMixin {

    @Inject(method= "sendMessage(Ljava/lang/String;)V", at=@At("HEAD"),cancellable = true)
    public void onMessagesend(String msg, CallbackInfo ci){
        if (msg.startsWith(".")) {
            String[] args = msg.toLowerCase().split(" +");
            String command = args[0].substring(1);
            MinecraftClient.getInstance().player.sendMessage(Text.of("Not sent!"), false);
            MinecraftClient.getInstance().player.sendMessage(Text.of("You ran the " + command + " command!"), false);
            ci.cancel();
        }
    }
}
