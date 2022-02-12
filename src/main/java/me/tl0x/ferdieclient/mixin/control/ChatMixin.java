package me.tl0x.ferdieclient.mixin.control;


import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.helpers.helper;
import me.tl0x.ferdieclient.base.reg.CommandReg;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ChatMixin {

    @Inject(method= "sendMessage(Ljava/lang/String;)V", at=@At("HEAD"),cancellable = true)
    public void onMessagesend(String msg, CallbackInfo ci){
        if (msg.startsWith("@")) {
            ci.cancel();
            String[] args = msg.toLowerCase().split(" +");
            String command = args[0].substring(1);

            Command toRun = CommandReg.getCommandbyName(command);
            if(toRun == null){
                helper.sendMessage("You found me");
            }
            else {
                toRun.onExecute(args);
            }
        }



    }
}
