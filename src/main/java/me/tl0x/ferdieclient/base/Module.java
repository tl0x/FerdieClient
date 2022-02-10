package me.tl0x.ferdieclient.base;

import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.util.Formatting;

public class Module {
    String name;
    String description;
    public boolean isEnabled = false;
    Keybind keybind = new Keybind(-1);

    public Module(String name, String description, Keybind keybind) {
        this.name = name;
        this.description = description;
        this.keybind = keybind;
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

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onTick() {

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
