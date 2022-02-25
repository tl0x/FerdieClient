package me.tl0x.ferdieclient;

import me.tl0x.ferdieclient.base.modules.util.ClickGuiModule;
import me.tl0x.ferdieclient.base.reg.CommandReg;
import me.tl0x.ferdieclient.base.reg.ConfigHandler;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import net.fabricmc.api.ModInitializer;
import me.tl0x.ferdieclient.base.bases.Module;

import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class FerdieClient implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "ferdieclient";
    public static final String MOD_NAME = "FerdieClient1.18.1";
    public static final MinecraftClient client = MinecraftClient.getInstance();
    public static final File SAVE = new File(FerdieClient.client.runDirectory, "FerdieClient");
    public static final String MOD_VERSION = "1.0.2";

    @Override
    public void onInitialize() {

        // MODULE AND COMMAND INIT

        log(Level.INFO, "Initializing");
        CommandReg.init();
        log(Level.INFO, "Initializing Commands");
        ModuleReg.init();

        // KEYBINDS

        Module clickgui = ModuleReg.getModulebyClass(ClickGuiModule.class);
        clickgui.setKeybind(344);


        log(Level.INFO, "Initializing Modules");
        //TODO: Initializer
//        IPCClient client = new IPCClient(940786718594969662L);
//        client.setListener(new IPCListener() {
//            @Override
//            public void onReady(IPCClient client) {
//                RichPresence.Builder builder = new RichPresence.Builder();
//                builder.setState("https://github.com/tl0x");
//                builder.setLargeImage("freddiedonut");
//                builder.setStartTimestamp(OffsetDateTime.now());
//                builder.setDetails("FerdieClient (1.18) 1.0.0");
//                client.sendRichPresence(builder.build());
//            }
//        });
//        try {
//            client.connect();
//        } catch (Exception e) {
//            log(Level.INFO, "An error occured when starting RPC");
//        }

        if (SAVE.exists() && !SAVE.isDirectory()) {
            SAVE.delete();
        }

        if (!SAVE.exists()) {
            SAVE.mkdir();
         }
        ConfigHandler.initFile();
        ConfigHandler.initKeyFile();
    }



    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}