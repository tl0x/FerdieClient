package me.tl0x.ferdieclient.helpers.Gui.elements;

import me.tl0x.ferdieclient.FerdieClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.Level;

import java.awt.*;

public class Button extends ClickableWidget implements Drawable {

    int x;
    int y;
    int width;
    int height;
    Color color;
    Color textColor;
    Text message;
    final Runnable a;

    public Button(int x, int y, int width, int height, Text message, Color color, Color textColor, Runnable a) {
        super(x, y, width, height, message);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.message = message;
        this.color = color;
        this.textColor = textColor;
        this.a = a;
    }

    public boolean inBounds(double mouseX, double mouseY) {
        return mouseX > x && mouseX < x+width && mouseY > y && mouseY < y+height;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (this.visible) {
            renderButton(matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        if (inBounds(mouseX, mouseY)) {
            a.run();
        }
        super.onClick(mouseX, mouseY);
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        TextRenderer textRenderer = FerdieClient.client.textRenderer;
        DrawableHelper.fill(matrices, x, y, x+width, y+height, color.getRGB());
        DrawableHelper.drawCenteredText(matrices, textRenderer, message, this.x + (this.width / 2), this.y + (this.height - 8) / 2, textColor.getRGB());
    }


    @Override
    public void appendNarrations(NarrationMessageBuilder builder) {

    }

    @Override
    public void playDownSound(SoundManager soundManager) {

    }
}
