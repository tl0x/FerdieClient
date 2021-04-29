package me.tl0x.main.command;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import me.tl0x.main.etc.reg.ModuleReg;

public class Help extends Command{

    public Help() { super("Help", "Brings a list of commands",new String[]{"Help,list"}); }

    @Override
    public void onExecute(String[] args) {
        for(int i = 0; i < ModuleReg.getModules().size(); i++){
            CHelper.sendMessage(ModuleReg.getModules().get(i).getName() + " - " + ModuleReg.getModules().get(i).getDesc());
        }
        super.onExecute(args);
    }
}
