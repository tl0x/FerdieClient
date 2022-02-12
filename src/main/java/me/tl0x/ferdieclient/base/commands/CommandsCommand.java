package me.tl0x.ferdieclient.base.commands;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.helpers.helper;
import me.tl0x.ferdieclient.base.reg.CommandReg;

import java.util.List;

public class CommandsCommand extends Command {

    public CommandsCommand() {
        super("Commands", "Brings out the list of commands", new String[]{"c"});
    }

    @Override
    public void onExecute(String[] args) {
        List<Command> commands = CommandReg.getCommands();
        for (int i = 0; i < commands.size(); i++) {
            helper.sendMessage(commands.get(i).getName() + ": " + commands.get(i).getDesc());
        }
    }
}
