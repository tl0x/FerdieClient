package me.tl0x.ferdieclient.base.reg;

import me.tl0x.ferdieclient.base.modules.*;
import me.tl0x.ferdieclient.base.Module;

import java.util.ArrayList;
import java.util.List;

public class ModuleReg {
    static List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.add(new ClickGuiModule());
        modules.add(new FakeHackerModule());
        modules.add(new HudModule());
        modules.add(new SpeedMineModule());
        modules.add(new SpeedModule());
        modules.add(new XCarryModule());
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




}
