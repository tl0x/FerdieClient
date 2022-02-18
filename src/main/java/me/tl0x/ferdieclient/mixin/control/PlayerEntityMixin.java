package me.tl0x.ferdieclient.mixin.control;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.modules.exploit.SpeedModule;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import me.tl0x.ferdieclient.base.Module;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method="getMovementSpeed", at=@At("HEAD"), cancellable = true)
    public void getMovementSpeed(CallbackInfoReturnable<Float> cir) {
        Module m = ModuleReg.getModulebyName("Speed");
        if (m.isEnabled && FerdieClient.client.player != null && FerdieClient.client.world != null) {
            if (FerdieClient.client.player.isSneaking()) {
                cir.setReturnValue(SpeedModule.sneakingSpeed);
            }
            cir.setReturnValue(SpeedModule.speed);
        }

    }
}
