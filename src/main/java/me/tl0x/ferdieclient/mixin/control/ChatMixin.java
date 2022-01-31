package me.tl0x.ferdieclient.mixin.control;


import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public class ChatMixin {

    @Inject(method= "sendMessage(Ljava/lang/String;)V", at=@At("HEAD"),cancellable = true)
    public void onMessagesend(String msg, CallbackInfo ci){

    }
}
