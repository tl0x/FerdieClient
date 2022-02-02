package me.tl0x.ferdieclient.commands;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.helpers.helper;
import me.tl0x.ferdieclient.reg.ModuleReg;
import me.tl0x.ferdieclient.base.Module;

import java.util.List;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("toggle","toggles a module",new String[]{});
    }

    @Override
    public void onExecute(String[] args) {

        if (args.length < 2) {
            helper.sendMessage("Use the correct syntax");
        } else {
            String query = args[1];
            Module module = ModuleReg.getModulebyName(query);
            module.toggle();
        }
    }
}
