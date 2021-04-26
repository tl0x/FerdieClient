package me.tl0x.main.mixin.control;

import me.tl0x.main.etc.reg.ModuleReg;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import me.tl0x.main.etc.based.Module;

@Mixin(ClientPlayerEntity.class)
public class TickMixin {
    @Inject(method="tick",at=@At("TAIL"))
    public void tick(CallbackInfo ci) {
        for(Module m: ModuleReg.getModules()) {
            if (m.isEnabled) m.OnTick();
        }
    }
}
