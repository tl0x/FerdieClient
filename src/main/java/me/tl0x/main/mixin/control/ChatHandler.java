package me.tl0x.main.mixin.control;


import me.tl0x.main.Config;
import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import me.tl0x.main.etc.reg.CommandRegistry;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ChatHandler {
    @Inject(method = "sendMessage(Ljava/lang/String;)V", at = @At("HEAD"), cancellable = true)
    public void sendChatMessageOverride(String message, CallbackInfo ci) {
        if (message.toLowerCase().startsWith(Config.prefix)) {
            ci.cancel();
            String[] args = message.substring(Config.prefix.length()).split(" +");
            String command = args[0].toLowerCase();
            Command toExecute = CommandRegistry.getCommandbyTrigger(command);
            if (toExecute == null) {
                CHelper.sendMessage("Command not found");
                return;
            }
            toExecute.onExecute(args);
        }
    }
}
