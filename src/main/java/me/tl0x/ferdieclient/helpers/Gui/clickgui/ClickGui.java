package me.tl0x.ferdieclient.helpers.Gui.clickgui;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.helpers.Gui.elements.Button;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apache.logging.log4j.Level;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClickGui extends Screen {
    public ClickGui() {
        super(Text.of(""));
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        Color color1 = new Color(0,0,0);
        Color color2 = new Color(219, 37, 255);

        List<Button> buttons = new ArrayList<>();
        int y = 0;
        for (Module m : ModuleReg.getModules()) {
            Text modName = null;
            if (m.isEnabled) {
                modName = Text.of(m.getName() + ": " + Formatting.GREEN + "On");
            } else if (!m.isEnabled) {
                modName = Text.of(m.getName() + ": " + Formatting.RED + "Off");
            }
            if (modName != null) {
                Button temp = new Button((int) 11*(width/12)-70, 0 + y, 70, 20, modName, color1, color2, () -> {
                    Module.toggle(m);
                });
                buttons.add(temp);
            }
            y += 20;

        }

        for (Button b: buttons) {
            this.addDrawableChild(b);
        }


        super.render(matrices, mouseX, mouseY, delta);
    }
}
