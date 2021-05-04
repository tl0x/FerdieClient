package me.tl0x.main.modules;

import me.tl0x.main.etc.based.Keybind;
import me.tl0x.main.etc.based.Module;

public class Hud extends Module {
    public static boolean enabled = false;

    public Hud() {
        super("Hud", "Activates the hud");
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
