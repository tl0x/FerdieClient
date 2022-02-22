package me.tl0x.ferdieclient.mixin.control;


import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.base.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Mixin(InGameHud.class)
public abstract class InGameGuiMixin {

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow
    private int scaledWidth;

    @Shadow
    private int scaledHeight;

    @Inject(method="render", at=@At("RETURN"))
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        int a = 2;

        Color coords = new Color(52, 235, 134);

        PlayerEntity pos = FerdieClient.client.player;
        int x = pos.getBlockX();
        int y = pos.getBlockY();
        int z = pos.getBlockZ();

        Module GuiModule = ModuleReg.getModulebyName("hud");
        if(GuiModule.isEnabled) {
            MinecraftClient.getInstance().textRenderer.draw(matrices, Text.of("FerdieClient"), 12, scaledHeight/12-10, 16733525);
            for(int i = 0; i < ModuleReg.getModules().size(); i++) {
                if (ModuleReg.getModules().get(i).isEnabled) {
                    MinecraftClient.getInstance().textRenderer.draw(matrices, Text.of(ModuleReg.getModules().get(i).getName()), 12, scaledHeight/8 + a, 3633115);
                    a += 10;
                }
            }

            FerdieClient.client.textRenderer.draw(matrices, Text.of("Coords: " + Integer.toString(x) + " " + Integer.toString(y) + " " + Integer.toString(z)), scaledWidth - 140, scaledHeight/12-10, coords.getRGB());

        }
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
