package me.tl0x.ferdieclient.base.reg;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.bases.Module;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigHandler {

    public static void initFile() {
        File CONFIG_FILE = new File(FerdieClient.SAVE, "config.txt");
        try {
            if (!CONFIG_FILE.exists()) {
                CONFIG_FILE.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initKeyFile() {
        File KEYBIND_FILE = new File(FerdieClient.SAVE, "keybinds.txt");
        try {
            if (!KEYBIND_FILE.exists()) {
                KEYBIND_FILE.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveKeybinds() throws IOException {
        File KEYBIND_FILE = new File(FerdieClient.SAVE, "keybinds.txt");

        Map<Module, Integer> keybinds = ModuleReg.getKeybinds();

        FileWriter writer = new FileWriter(KEYBIND_FILE);
        BufferedWriter w = new BufferedWriter(writer);

        for (Module m: keybinds.keySet()) {
            int keyCode = keybinds.get(m);
            w.write(m.getName() + " ");
            w.write(keyCode + "\n");
        }
        w.close();
        writer.close();
    }

    public static void loadKeybinds() throws IOException {
        File KEYBIND_FILE = new File(FerdieClient.SAVE, "keybinds.txt");
        BufferedReader b = new BufferedReader(new FileReader(KEYBIND_FILE));
        String line;
        while ((line = b.readLine()) != null) {
            String[] tba = StringUtils.split(line);
            Module m = ModuleReg.getModulebyName(tba[0]);
            int key = Integer.parseInt(tba[1]);

            m.setKeybind(key);
        }

        KEYBIND_FILE.delete();
        KEYBIND_FILE.createNewFile();

    }

    public static void saveActiveMods() throws IOException {
        List<Module> modules = ModuleReg.getModules();
        
        File CONFIG_FILE = new File(FerdieClient.SAVE, "config.txt");

        FileWriter writer = new FileWriter(CONFIG_FILE);
        BufferedWriter w = new BufferedWriter(writer);
        for(Module m: modules) {
            if (m.isEnabled) {
                w.write(m.getName());
                w.write("\n");
            }
        }
        w.close();
        writer.close();
        
    }

    public static List<Module> loadActiveMods() {

        File CONFIG_FILE = new File(FerdieClient.SAVE, "config.txt");
        List<Module> tbt = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(CONFIG_FILE));
            String line;
            while ((line = br.readLine()) != null) {
                Module m = ModuleReg.getModulebyName(line);
                if (m != null) {
                    tbt.add(m);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            FerdieClient.log(Level.ERROR, "File not found. Try creating it next time?");
            e.printStackTrace();
        } catch (Exception e) {
            FerdieClient.log(Level.ERROR, "An error occured bozo (when loading mods)");
        }

        CONFIG_FILE.delete();
        try {
            CONFIG_FILE.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tbt;
    }



}
