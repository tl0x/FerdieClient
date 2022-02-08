package me.tl0x.ferdieclient.base.reg;

import me.tl0x.ferdieclient.base.modules.GuiModule;
import me.tl0x.ferdieclient.base.Module;

import java.util.ArrayList;
import java.util.List;

public class ModuleReg {
    static List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.add(new GuiModule());
    }

    public static List<Module> getModules() {
        return modules;
    }

    public static Module getModulebyName(String query) {
        Module m = null;
        for(int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getName().equalsIgnoreCase(query)) {
                m = modules.get(i);
            }
        }

        return m;
    }




}
