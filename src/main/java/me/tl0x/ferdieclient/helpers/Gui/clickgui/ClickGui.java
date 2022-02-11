package me.tl0x.ferdieclient.helpers.Gui.clickgui;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.helpers.render.Renderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.awt.*;

public class ClickGui extends Screen {
    public ClickGui() {
        super(Text.of(""));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        Color color = new Color(255,255,255);
        Renderer.renderRoundedQuadInternal(matrices.peek().getPositionMatrix(),255f,255f,255f,0.5f,0,0,width,height,10,5);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
