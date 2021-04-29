package me.tl0x.main.etc.reg;

import me.tl0x.main.command.*;
import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    static List<Command> commands = new ArrayList<>();

    public static void init() {
        commands.add(new Toggle());
        commands.add(new Help());
    }

    public static List<Command> getCommands() {
        return commands;
    }

    public static Command getCommandbyTrigger(String query) {
        Command ret = null;
        for (Command c : commands) {
            if (c.getDisplayName().equalsIgnoreCase(query)) {ret = c;}
        }
        return ret;
    }


}

