package me.tl0x.ferdieclient.base.bases;

import me.tl0x.ferdieclient.base.modules.ModuleType;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.util.math.MatrixStack;
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
        helper.sendMessage("Toggled " + this.getName() + Formatting.GREEN + " On");
    }

    public void onDisable() {
        helper.sendMessage("Toggled " + this.getName() + Formatting.RED + " Off");
    }

    public void onTick() {

    }

    public void onRender(MatrixStack matrices, float tickDelta) {

    }

    public void setKeybind(int keyCode) {
        this.keybind = new Keybind(keyCode);
        ModuleReg.registerKeybind(this, keyCode);
    }

    public static void toggle(Module m) {
        if (!m.isEnabled) {
            m.isEnabled = true;
            m.onEnable();
        } else {
            m.isEnabled = false;
            m.onDisable();
        }
    }

}
