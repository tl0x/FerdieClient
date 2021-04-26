package me.tl0x.main.etc.reg;

import me.tl0x.main.command.Toggle;
import me.tl0x.main.etc.based.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    static List<Command> commands = new ArrayList<>();

    public static void init() {
        commands.add(new Toggle());
    }

    public static List<Command> getCommands() {
        return commands;
    }

    public static Command getCommandbyTrigger(String query) {
        Command ret = null;
        for (Command c : commands) {
            for (String alias : c.getAliases()) {
                if (alias.equalsIgnoreCase(query)) {
                    ret = c;
                    break;
                }
            }
        }
        return ret;
    }


}

