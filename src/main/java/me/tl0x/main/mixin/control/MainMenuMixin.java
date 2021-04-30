package me.tl0x.main.mixin.control;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import me.tl0x.main.FerdieClient;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mixin(net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen.class)
public class MainMenuMixin extends Screen {
    ButtonWidget bwd;

    protected MainMenuMixin(Text title) {super(title);}

    @Inject(at=@At("RETURN"), method = "init")
    public void render(CallbackInfo ci) {
        RenderSystem.enableBlend();
        ButtonWidget bw = new ButtonWidget(1,height-21,100,20,Text.of("AHHHH"), (buttonWidget) -> {
            FerdieClient.log(Level.INFO,"Button pressed");
        });
        this.addButton(bw);

        bwd = bw;
        RenderSystem.disableBlend();
    }
}
