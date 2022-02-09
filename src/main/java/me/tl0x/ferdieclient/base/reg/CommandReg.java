package me.tl0x.ferdieclient.base.reg;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.base.commands.HelpCommand;
import me.tl0x.ferdieclient.base.commands.SpamCommand;
import me.tl0x.ferdieclient.base.commands.TestCommand;
import me.tl0x.ferdieclient.base.commands.ToggleCommand;

import java.util.ArrayList;
import java.util.List;

public class CommandReg {
    public static List<Command> commands = new ArrayList<>();

    public static void init() {
        commands.add(new TestCommand());
        commands.add(new HelpCommand());
        commands.add(new SpamCommand());
        commands.add(new ToggleCommand());
    }

    public static List<Command> getCommands() {
        return commands;
    }

    public static Command getCommandbyName(String name) {
        Command ans = null;
        for(int i = 0; i < commands.size(); i++){
            if (commands.get(i).getName().equalsIgnoreCase(name)){
                ans = commands.get(i);
            }
        }
        return ans;
    }
}