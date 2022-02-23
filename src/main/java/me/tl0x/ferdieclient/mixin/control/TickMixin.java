package me.tl0x.ferdieclient.mixin.control;

import me.tl0x.ferdieclient.base.bases.Module;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class TickMixin {
    @Shadow @Final public ClientPlayNetworkHandler networkHandler;

    @Inject(method="tick", at=@At("TAIL"))
    public void tick(CallbackInfo ci) {
        for (Module m : ModuleReg.getModules()) {
            if(m.isEnabled) {
                m.onTick();
            }
        }
    }

    @Inject(method="startRiding", at=@At("HEAD"))
    public void startRiding(Entity entity, boolean force, CallbackInfoReturnable<Boolean> ci) {
        helper.sendMessage("Riding");

    }
}
