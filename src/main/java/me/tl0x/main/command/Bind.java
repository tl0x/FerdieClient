package me.tl0x.main.command;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import me.tl0x.main.etc.based.Module;
import me.tl0x.main.etc.reg.ModuleReg;


public class Bind extends Command {
    public Bind() {
        super("Bind","Sets a bind for your modules",new String[]{});
    }

    @Override
    public void onExecute(String[] args) {
        if(args.length < 2){
            CHelper.sendMessage("ur bad, git gud syntax");
            return;
        }
        else {
            String modname = args[1];
            Module module = ModuleReg.getModulebyName(modname);
            if (module == null) return;
            else {
                module.setKeybind((int) args[2].toUpperCase().charAt(0));
                CHelper.sendMessage(modname + " set to " + args[2]);
            }
        }
        super.onExecute(args);
    }
}
