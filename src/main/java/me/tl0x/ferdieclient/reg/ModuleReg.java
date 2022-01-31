package me.tl0x.ferdieclient.reg;

import me.tl0x.ferdieclient.modules.TestModule;
import me.tl0x.ferdieclient.base.Module;

import java.util.ArrayList;
import java.util.List;

public class ModuleReg {
    static List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.add(new TestModule());
    }

    public static List<Module> getModules() {
        return modules;
    }

    public void toggle(Module m) {
        m.isEnabled = true;
    }




}
