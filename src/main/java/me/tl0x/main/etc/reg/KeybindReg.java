package me.tl0x.main.etc.reg;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Keybind;
import me.tl0x.main.FerdieClient;
import me.tl0x.main.etc.based.Module;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KeybindReg {
    public static Map<String, Keybind> binds = new HashMap<>();

    public static void init() {
        FerdieClient.log(Level.INFO,"Loading Keybinds");
        for(int i = 0; i < ModuleReg.getModules().size(); i++){
            if (ModuleReg.getModules().get(i).keybind == null){
                return;
            }
            if(ModuleReg.getModules().get(i).keybind.keycode != -1){
                binds.put(ModuleReg.getModules().get(i).getName(),ModuleReg.getModules().get(i).keybind);
            }
        }
    }

    public static void tick() {
        binds.forEach((m,keybind) -> {
            if (keybind.isPressed() && MinecraftClient.getInstance().player != null) {
                Module module = ModuleReg.getModulebyName(m);
                module.toggle();
            }
        });
    }

    public static void reload() {
        binds.clear();
        init();
    }
}
