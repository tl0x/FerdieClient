package me.tl0x.main.command;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import me.tl0x.main.etc.based.Module;
import me.tl0x.main.etc.reg.ModuleReg;


public class Toggle extends Command {

    public Toggle() {
        super("Toggle", "Test command", new String[]{"toggle"});
    }

    @Override
    public void onExecute(String[] args) {
        if (args.length < 2) {
            CHelper.sendMessage("What module do you want?");
            return;
        }

        Module m = ModuleReg.getModulebyName(args[1]);
        if (m == null) {
            CHelper.sendMessage("Invalid Module!");
            return;
        }
        m.toggle();

    }

}
