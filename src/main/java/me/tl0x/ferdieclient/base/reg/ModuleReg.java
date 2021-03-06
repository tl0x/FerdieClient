package me.tl0x.ferdieclient.base.reg;

import me.tl0x.ferdieclient.base.bases.Module;
import me.tl0x.ferdieclient.base.modules.TestModule;
import me.tl0x.ferdieclient.base.modules.exploit.*;
import me.tl0x.ferdieclient.base.modules.misc.AutoEzModule;
import me.tl0x.ferdieclient.base.modules.misc.FakeHackerModule;
import me.tl0x.ferdieclient.base.modules.render.FullBrightModule;
import me.tl0x.ferdieclient.base.modules.render.TracersModule;
import me.tl0x.ferdieclient.base.modules.util.AutoElytraModule;
import me.tl0x.ferdieclient.base.modules.util.ClickGuiModule;
import me.tl0x.ferdieclient.base.modules.util.HudModule;
import me.tl0x.ferdieclient.base.modules.util.MuteChatModule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleReg {
    static List<Module> modules = new ArrayList<>();
    static Map<Module, Integer> keybinds = new HashMap<>();

    public static void init() {
        modules.add(new AutoElytraModule());
        modules.add(new AutoEzModule());
        modules.add(new ClickGuiModule());
        modules.add(new DupeModule());
        modules.add(new FakeHackerModule());
        modules.add(new FlightModule());
        modules.add(new FullBrightModule());
        modules.add(new HudModule());
        modules.add(new SpeedMineModule());
        modules.add(new SpeedModule());
        modules.add(new TracersModule());
        modules.add(new NoFallModule());
        modules.add(new TestModule());
        modules.add(new XCarryModule());
        modules.add(new Triggerbot());
        modules.add(new ExposedXrayModule());
        modules.add(new MuteChatModule());
    }



    public static List<Module> getModules() {
        return modules;
    }

    public static Module getModulebyName(String query) {
        Module ret = null;
        for (Module c : modules) {
            if (c.getName().equalsIgnoreCase(query)) ret = c;
        }
        return ret;
    }

    public static Module getModulebyClass(Class clazz) {
        for (Module c: modules){
            if (c.getClass() == clazz) {
                return c;
            }
        }
        return null;
    }


    public static void registerKeybind(Module m, int keyCode) {
        keybinds.put(m, keyCode);
    }

    public static Map<Module, Integer> getKeybinds() {
        return keybinds;
    }
}
