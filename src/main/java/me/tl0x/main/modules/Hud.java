package me.tl0x.main.modules;

import me.tl0x.main.etc.based.Module;

public class Hud extends Module {
    public Hud() {
        super("Hud","Activates the hud");
    }
    public static boolean enabled = false;
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
