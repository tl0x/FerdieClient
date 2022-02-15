package me.tl0x.ferdieclient.mixin.control;


import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.base.Module;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(at =
            @At("HEAD"), method = "renderWorld")
    public void renderWorld(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo ci) {
        for(Module m: ModuleReg.getModules()) {
            if (m.isEnabled) m.onRender(matrices, tickDelta);
        }
    }

}
