package me.tl0x.main.command;

import com.sun.org.apache.xpath.internal.operations.Mod;
import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import me.tl0x.main.etc.based.Module;
import me.tl0x.main.etc.reg.ModuleReg;
import net.minecraft.util.Formatting;

public class Panic extends Command {
    public Panic() {
        super("Panic","Disables all modules",new String[]{});
    }

    @Override
    public void onExecute(String[] args) {
        for(int i = 0; i < ModuleReg.getModules().size(); i++){
            if (ModuleReg.getModules().get(i).isEnabled = true) {
                ModuleReg.getModules().get(i).isEnabled = false;
                ModuleReg.getModules().get(i).onDisable();
                CHelper.sendMessage("Toggled " + ModuleReg.getModules().get(i).getName() + Formatting.RED + " Off");
            }
        }
        super.onExecute(args);
    }
}
