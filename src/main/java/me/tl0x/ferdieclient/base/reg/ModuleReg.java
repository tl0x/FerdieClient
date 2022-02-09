package me.tl0x.ferdieclient.base.reg;

import me.tl0x.ferdieclient.base.modules.FakeHackerModule;
import me.tl0x.ferdieclient.base.modules.Hud;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.base.modules.SpeedMineModule;
import me.tl0x.ferdieclient.base.modules.SpeedModule;

import java.util.ArrayList;
import java.util.List;

public class ModuleReg {
    static List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.add(new FakeHackerModule());
        modules.add(new Hud());
        modules.add(new SpeedMineModule());
        modules.add(new SpeedModule());
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
        for (Module c: modules ){
            if (c.getClass() == clazz) {
                return c;
            }
        }
        return null;
    }




}
