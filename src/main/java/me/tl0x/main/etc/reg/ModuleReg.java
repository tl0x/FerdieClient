package me.tl0x.main.etc.reg;

import me.tl0x.main.etc.based.Module;
import me.tl0x.main.modules.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleReg {
    static List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.add(new Freeze());
        modules.add(new Flight());
        modules.add(new Test());
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


}
