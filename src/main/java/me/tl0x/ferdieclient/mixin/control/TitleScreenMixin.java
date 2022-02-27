package me.tl0x.ferdieclient.mixin.control;

import me.tl0x.ferdieclient.helpers.font.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(TitleScreen.class)
public class TitleScreenMixin extends Screen {

    public TitleScreenMixin() {
        super(Text.of(""));
    }
    Color color = new Color(113, 229, 239);

    @Inject(method="render", at=@At("TAIL"))
    public void renderText(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        FontRenderer.getRenderer(20).drawString(matrices, "Running FerdieClient by tl0x", 10,10, color.getRGB(), true);
    }
}
