package me.tl0x.ferdieclient.helpers.Gui;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.awt.*;

public class TestGui extends Screen {
    public TestGui() {
        super(Text.of(""));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        DrawableHelper.fill(matrices,0,0,width,height, Color.RED.getRGB());
        super.render(matrices, mouseX, mouseY, delta);
    }
}
