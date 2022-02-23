package me.tl0x.ferdieclient.helpers.Gui.screens.clickgui;

import me.tl0x.ferdieclient.base.bases.Module;
import me.tl0x.ferdieclient.base.modules.ModuleType;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.helpers.Gui.elements.Button;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClickGui extends Screen {

    public static ClickGui instance = null;
    private ClickGui() {
        super(Text.of(""));
    }

    public static ClickGui getInstance() {
        if (instance == null) {
            instance = new ClickGui();
        }
        return instance;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        Color color1 = new Color(0,0,0);
        Color color2 = new Color(219, 37, 255);

        List<Button> buttons = new ArrayList<>();
        int y = 0;
        int y2 = 0;
        int y3 = 0;
        int y4 = 0;


        for (Module m : ModuleReg.getModules()) {
            Text modName = null;
            if (m.isEnabled) {
                modName = Text.of(m.getName() + ": " + Formatting.GREEN + "On");
            } else if (!m.isEnabled) {
                modName = Text.of(m.getName() + ": " + Formatting.RED + "Off");
            }
            if (modName != null) {
                if (m.getModuleType() == ModuleType.MODULETYPE_EXPLOIT) {
                    Button temp = new Button((int) 11 * (width / 12) - 70, 0 + y, 70, 20, modName, color1, color2, () -> {
                        Module.toggle(m);
                    });
                    buttons.add(temp);
                    y += 20;
                } else if (m.getModuleType() == ModuleType.MODULETYPE_MISC) {
                    Button temp = new Button((int) 9 * (width / 12) - 70, 0 + y2, 70, 20, modName, color1, color2, () -> {
                        Module.toggle(m);
                    });
                    buttons.add(temp);
                    y2 += 20;
                } else if (m.getModuleType() == ModuleType.MODULETYPE_RENDER) {
                    Button temp = new Button((int) 7 * (width / 12) - 70, 0 + y3, 70, 20, modName, color1, color2, () -> {
                        Module.toggle(m);
                    });
                    buttons.add(temp);
                    y3 += 20;
                } else if (m.getModuleType() == ModuleType.MODULETYPE_UTIL) {
                    Button temp = new Button((int) 5 * (width / 12) - 70, 0 + y4, 70, 20, modName, color1, color2, () -> {
                        Module.toggle(m);
                    });
                    buttons.add(temp);
                    y4 += 20;
                }
            }


        }

        for (Button b: buttons) {
            this.addDrawableChild(b);
        }


        super.render(matrices, mouseX, mouseY, delta);
    }
}
