package me.tl0x.ferdieclient.base.commands;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.helpers.helper;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.base.Module;
import net.minecraft.util.Formatting;

public class ToggleCommand extends Command {

    public ToggleCommand() {
        super("toggle","toggles a module",new String[]{});
    }

    public static void toggle(Module m) {
        if (!m.isEnabled) {
            helper.sendMessage("Toggled " + m.getName() + Formatting.GREEN + " On");
            m.isEnabled = true;
            m.onEnable();
        } else {
            helper.sendMessage("Toggled " + m.getName() + Formatting.RED + " Off");
            m.isEnabled = false;
            m.onDisable();
        }
    }

    @Override
    public void onExecute(String[] args) {

        if (args.length < 2) {
            helper.sendMessage("Use the correct syntax");
            return;
        } else {
            String query = args[1];
            Module module = ModuleReg.getModulebyName(query);
            if (module == null) {
                helper.sendMessage("Invalid Module!");
                return;
            }
            toggle(module);
        }
    }
}
