package me.tl0x.ferdieclient.mixin.control;


import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.helpers.Gui.elements.Button;
import me.tl0x.ferdieclient.helpers.Gui.screens.AltScreen;
import me.tl0x.ferdieclient.helpers.font.FontRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin extends Screen {

    public MultiplayerScreenMixin() {
        super(Text.of(""));
    }

    @Inject(method="init", at=@At("RETURN"))
    public void init(CallbackInfo ci) {
        Button alts = new Button(10,10, 75, 30, Text.of("Login"), Color.DARK_GRAY, Color.WHITE, () -> {
            FerdieClient.client.setScreen(AltScreen.getInstance());
        });

        this.addDrawableChild(alts);
    }
}
