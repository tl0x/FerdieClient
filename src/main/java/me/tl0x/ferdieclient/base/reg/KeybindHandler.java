package me.tl0x.ferdieclient.base.reg;

import me.tl0x.ferdieclient.base.bases.Module;


public class KeybindHandler {


    public static void fire(int keyCode, int action) {
        if (keyCode == -1) return;
        for (Module m : ModuleReg.getModules()) {
            int key = m.getKeybind().getKeyCode();
            if (key == keyCode && action == 1) {
                Module.toggle(m);
            }
        }
    }
}
