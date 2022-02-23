package me.tl0x.ferdieclient.mixin.control;

import me.tl0x.ferdieclient.base.reg.ConfigHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import me.tl0x.ferdieclient.base.bases.Module;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method="init", at=@At("TAIL"))
    public void init(CallbackInfo ci) {
        List<Module> mods = ConfigHandler.loadActiveMods();
        for (Module m: mods) {
            if (!m.isEnabled) {
                m.onEnable();
                m.isEnabled = true;
            }
        }
    }

}
