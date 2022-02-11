package me.tl0x.ferdieclient.base.commands;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.helpers.helper;

public class BindCommand extends Command {

    public BindCommand() {
        super("bind", "Binds a module to a keybind .bind module key", new String[]{});
    }

    @Override
    public void onExecute(String[] args) {
        try {
            if (args.length < 2) {
                helper.sendMessage("Invalid input");
                return;
            } else {
                Module m = ModuleReg.getModulebyName(args[1]);
                m.setKeybind((int) args[2].toUpperCase().charAt(0));
            }
        } catch (Exception e) {
            helper.sendMessage("Error occured when setting bind");
        }
        super.onExecute(args);
    }
}
