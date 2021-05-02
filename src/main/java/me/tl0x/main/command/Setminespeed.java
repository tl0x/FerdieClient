package me.tl0x.main.command;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import me.tl0x.main.modules.SpeedMine;

public class Setminespeed extends Command {
    public Setminespeed() {
        super("SetMineSpeed","sets speedmine mining speed", new String[]{"speedy"});
    }

    @Override
    public void onExecute(String[] args) {
        if (args.length < 2) {
            CHelper.sendMessage("What speed do you want?");
            return;
        }
        else {
            SpeedMine.speed = Integer.parseInt(args[1]);
            CHelper.sendMessage("Mining speed set to " + args[1]);
        }
        super.onExecute(args);
    }
}
