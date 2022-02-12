package me.tl0x.ferdieclient.mixin.control;


import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.reg.ConfigHandler;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method="stop", at=@At("HEAD"))
    public void stop(CallbackInfo ci) {
        FerdieClient.log(Level.INFO, "STOPPING THE CLIENT AND SAVING (PLS)");
        try {
            ConfigHandler.saveActiveMods();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

}
