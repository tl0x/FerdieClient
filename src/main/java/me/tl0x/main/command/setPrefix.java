package me.tl0x.main.command;

import me.tl0x.main.Config;
import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;

public class setPrefix extends Command {
    public setPrefix() {
        super("SetPrefix", "sets the command prefix of the client. Default is @", new String[]{});
    }

    @Override
    public void onExecute(String[] args) {
        if (args.length < 2) {
            CHelper.sendMessage("Please type out a prefix.");
        }
        Config.prefix = args[1];
        CHelper.sendMessage("Command prefix set to " + args[1]);

    }
}
