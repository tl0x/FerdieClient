package me.tl0x.main.etc.based;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.reg.KeybindReg;
import net.minecraft.util.Formatting;

public class Module {
    public boolean isEnabled = false;
    String name;
    String desc;
    public Keybind keybind = new Keybind(-1);

    public Module(String n, String d) {
        this.name = n;
        this.desc = d;
    }

    public void OnTick() {
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public void toggle() {
        if (!isEnabled) {
            CHelper.sendMessage("Toggled " + this.getName() + Formatting.GREEN + " On");
            this.isEnabled = true;
            this.onEnable();
        } else {
            CHelper.sendMessage("Toggled " + this.getName() + Formatting.RED + " Off");
            this.isEnabled = false;
            this.onDisable();
        }
    }

    public void setKeybind(int kc) {
        this.keybind = new Keybind(kc);
        CHelper.sendMessage("Called");
        KeybindReg.reload();
    }

    public final String getName() {
        return name;
    }

    public final String getDesc() {
        return desc;
    }
}
