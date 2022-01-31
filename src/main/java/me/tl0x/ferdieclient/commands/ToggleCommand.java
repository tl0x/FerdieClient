package me.tl0x.ferdieclient.commands;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.reg.ModuleReg;
import me.tl0x.ferdieclient.base.Module;

import java.util.List;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("toggle","toggles a module",new String[]{});
    }

    @Override
    public void onExecute(String[] args) {
        Module module;
        String query = args[1];
        List<Module> modules= ModuleReg.getModules();
        for(int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getName().equalsIgnoreCase(query)) {
                module = modules.get(i);
            }
        }
    }
}
