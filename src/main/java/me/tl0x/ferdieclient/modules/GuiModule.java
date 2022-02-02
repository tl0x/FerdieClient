package me.tl0x.ferdieclient.modules;

import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.helper;

public class GuiModule extends Module {

    public static boolean enabled = false;
    public GuiModule() {
        super("Gui","Enables or Disables the GUI.");
    }

    @Override
    public void onEnable() {
        enabled = true;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        enabled = false;
        super.onDisable();
    }
}
