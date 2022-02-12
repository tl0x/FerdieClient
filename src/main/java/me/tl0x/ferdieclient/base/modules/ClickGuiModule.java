package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.Gui.clickgui.ClickGui;

public class ClickGuiModule extends Module {

    public ClickGuiModule() {
        super("ClickGui", "Gui to activate and edit modules");
        setModuleType(ModuleType.MODULETYPE_UTIL);
    }

    @Override
    public void onEnable() {
        FerdieClient.client.setScreen(new ClickGui());
        Module.toggle(this);
        super.onEnable();
    }
}
