package me.tl0x.ferdieclient.mixin.control;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.reg.KeybindHandler;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.KeyboardEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method="onKey", at=@At("HEAD"))
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (window == FerdieClient.client.getWindow().getHandle()) {
            EventHandler.fireEvent(EventType.KEYBOARD_EVENT, new KeyboardEvent(key,action));
            if (FerdieClient.client.player != null) {
                KeybindHandler.fire(key, action);
            }
        }
    }
}
