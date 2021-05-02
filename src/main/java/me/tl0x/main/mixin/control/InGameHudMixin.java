package me.tl0x.main.mixin.control;

import jdk.vm.ci.code.site.Call;
import me.tl0x.main.etc.reg.ModuleReg;
import me.tl0x.main.modules.Hud;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow private int scaledWidth;

    @Shadow private int scaledHeight;

    @Inject(method="render",at=@At("RETURN"))
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (Hud.enabled) {
            int color = 16733525;
            MinecraftClient.getInstance().textRenderer.draw(matrices, Text.of("FerdieClient 0.0.5"), 8, scaledHeight - 235, color);
            int offset = 2;
            for (int i = 0; i < ModuleReg.getModules().size(); i++) {
                if (ModuleReg.getModules().get(i).isEnabled) {
                    offset = offset + 10;
                    MinecraftClient.getInstance().textRenderer.draw(matrices, ModuleReg.getModules().get(i).getName(), 8, scaledHeight - 228 + offset, 3633115);
                }
            }
        }
    }
}
