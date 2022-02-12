package me.tl0x.ferdieclient.base;

import me.tl0x.ferdieclient.base.modules.ModuleType;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.util.Formatting;

public class Module {
    String name;
    String description;
    public boolean isEnabled = false;
    public Keybind keybind = new Keybind(-1);
    public ModuleType moduleType = ModuleType.MODULETYPE_UNKNOWN;

    public Module(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Keybind getKeybind() {
        return keybind;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }

    public void setModuleType(ModuleType m) {
        this.moduleType = m;
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onTick() {

    }

    public void setKeybind(int keyCode) {
        this.keybind = new Keybind(keyCode);
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

}
