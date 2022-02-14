package me.tl0x.ferdieclient.base.modules.util;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.base.modules.ModuleType;
import me.tl0x.ferdieclient.helpers.Gui.clickgui.ClickGui;
import net.minecraft.client.gui.screen.Screen;

public class ClickGuiModule extends Module {

    public ClickGuiModule() {
        super("ClickGui", "Gui to activate and edit modules");
        setModuleType(ModuleType.MODULETYPE_UTIL);
    }

    @Override
    public void onEnable() {
        Screen ClickGui = new ClickGui();
        FerdieClient.client.setScreen(ClickGui);
        Module.toggle(this);
    }
}
