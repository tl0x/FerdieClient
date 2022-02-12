package me.tl0x.ferdieclient.base.reg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Module;
import net.minecraft.client.realms.util.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConfigHandler {

    Gson gson = new Gson();

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
