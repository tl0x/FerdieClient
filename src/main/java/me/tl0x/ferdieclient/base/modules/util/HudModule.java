package me.tl0x.ferdieclient.base.modules.util;

import me.tl0x.ferdieclient.base.bases.Module;
import me.tl0x.ferdieclient.base.modules.ModuleType;

public class HudModule extends Module {


    public static boolean enabled = false;
    public HudModule() {
        super("Hud","Enables or Disables the in game HUD.");
        setModuleType(ModuleType.MODULETYPE_UTIL);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }
    @Override
    public void onTick() {
        super.onTick();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
