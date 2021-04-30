package me.tl0x.main.command;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import me.tl0x.main.etc.reg.ModuleReg;
import me.tl0x.main.etc.reg.CommandRegistry;
import net.minecraft.util.Formatting;

public class Help extends Command{

    public Help() { super("Help", "Brings a list of commands",new String[]{"Help,list"}); }

    @Override
    public void onExecute(String[] args) {
            CHelper.sendMessage(Formatting.BOLD + "List of Modules: ");
            for (int i = 0; i < ModuleReg.getModules().size(); i++) {
                CHelper.sendMessage(ModuleReg.getModules().get(i).getName() + " - " + ModuleReg.getModules().get(i).getDesc());
            }
            CHelper.sendMessage(Formatting.BOLD + "List of Commands: ");
            for (int i = 0; i < CommandRegistry.getCommands().size(); i++) {
                CHelper.sendMessage(CommandRegistry.getCommands().get(i).getDisplayName() + " - " + CommandRegistry.getCommands().get(i).getDescription());
            }
        super.onExecute(args);
    }
}
