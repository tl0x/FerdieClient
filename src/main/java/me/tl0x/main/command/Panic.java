package me.tl0x.main.command;

import me.tl0x.main.etc.based.Command;
import me.tl0x.main.etc.reg.ModuleReg;

public class Panic extends Command {
    public Panic() {
        super("Panic", "Disables all modules", new String[]{});
    }

    @Override
    public void onExecute(String[] args) {
        for (int i = 0; i < ModuleReg.getModules().size(); i++) {
            if (ModuleReg.getModules().get(i).isEnabled = true) {
                ModuleReg.getModules().get(i).toggle();
            }
        }
        super.onExecute(args);
    }
}
