package me.tl0x.ferdieclient.base;

import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.util.Formatting;

public class Module {
    String name;
    String description;
    public boolean isEnabled = false;

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

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void onTick() {

    }


}