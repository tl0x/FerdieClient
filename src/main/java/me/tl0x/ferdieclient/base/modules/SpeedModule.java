package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.base.Keybind;
import me.tl0x.ferdieclient.base.Module;

public class SpeedModule extends Module {

    public static float speed = 2.5f;

    public SpeedModule() {
        super("Speed", "Makes you fast", new Keybind(-1));
    }

    @Override
    public void onTick() {
        super.onTick();
    }
}
