package me.tl0x.ferdieclient.base.modules.util;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.base.modules.ModuleType;
import net.minecraft.client.gui.screen.Screen;

public class ClickGuiModule extends Module {

    public ClickGuiModule() {
        super("ClickGui", "Gui to activate and edit modules");
        setModuleType(ModuleType.MODULETYPE_UTIL);
    }

    @Override
    public void onEnable() {
        Screen ClickGui = me.tl0x.ferdieclient.helpers.Gui.screens.clickgui.ClickGui.getInstance();
        FerdieClient.client.setScreen(ClickGui);
        Module.toggle(this);
    }
}
