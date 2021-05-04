package me.tl0x.main.mixin.control;

import com.mojang.blaze3d.systems.RenderSystem;
import me.tl0x.main.FerdieClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen.class)
public class MainMenuMixin extends Screen {
    ButtonWidget bwd;

    protected MainMenuMixin(Text title) {
        super(title);
    }

    @Inject(at = @At("RETURN"), method = "init")
    public void render(CallbackInfo ci) {
        RenderSystem.enableBlend();
        ButtonWidget bw = new ButtonWidget(1, height - 21, 100, 20, Text.of("AHHHH"), (buttonWidget) -> {
            FerdieClient.log(Level.INFO, "Button pressed");
        });
        this.addButton(bw);

        bwd = bw;
        RenderSystem.disableBlend();
    }
}
