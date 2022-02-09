package me.tl0x.ferdieclient.mixin.control;


import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.base.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class InGameGuiMixin {

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow
    private int scaledWidth;

    @Shadow
    private int scaledHeight;

    @Inject(method="render", at=@At("RETURN"))
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        int x = 2;
        Module GuiModule = ModuleReg.getModulebyName("hud");
        if(GuiModule.isEnabled) {
            MinecraftClient.getInstance().textRenderer.draw(matrices, Text.of("FerdieClient"), 8, scaledHeight-235, 16733525);
            for(int i = 0; i < ModuleReg.getModules().size(); i++) {
                if (ModuleReg.getModules().get(i).isEnabled) {
                    MinecraftClient.getInstance().textRenderer.draw(matrices, Text.of(ModuleReg.getModules().get(i).getName()), 8, scaledHeight-220 + x, 3633115);
                    x += 10;
                }
            }
        }
    }
}
