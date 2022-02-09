package me.tl0x.ferdieclient.base.commands;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.helpers.helper;
import me.tl0x.ferdieclient.base.reg.CommandReg;

import java.util.List;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("Help", "Brings out the list of commands", new String[]{"h"});
    }

    @Override
    public void onExecute(String[] args){
        if (args.length < 2) {
            return;
        }
        if (args[1] == "1") {
            List<Command> commands = CommandReg.getCommands();
            for (int i = 0; i < commands.size(); i++) {
                helper.sendMessage(commands.get(i).getName() + ": " + commands.get(i).getDesc());
            }
        } else if (args[1] == "2"){
            List<Module> modules = ModuleReg.getModules();
            for (int i = 0; i < modules.size(); i++) {
                helper.sendMessage(modules.get(i).getName() + ": " + modules.get(i).getDescription());
            }
        } else {
            helper.sendMessage("Invalid syntax!");
        }
    }

}
