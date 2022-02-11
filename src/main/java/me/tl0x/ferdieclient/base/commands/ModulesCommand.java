package me.tl0x.ferdieclient.base.commands;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.helpers.helper;

import java.util.List;

public class ModulesCommand extends Command {

    public ModulesCommand() {
        super("modules", "Prints a list of modules and their description", new String[]{});
    }

    @Override
    public void onExecute(String[] args) {
        List<Module> modules = ModuleReg.getModules();
        for (Module m: modules) {
            helper.sendMessage(m.getName() + ": " + m.getDescription());
        }
        super.onExecute(args);
    }
}
