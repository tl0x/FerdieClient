package me.tl0x.main;

import me.tl0x.main.etc.reg.CommandRegistry;
import me.tl0x.main.etc.reg.KeybindReg;
import me.tl0x.main.etc.reg.ModuleReg;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FerdieClient implements ModInitializer {

    public static final String MOD_ID = "ferdieclient";
    public static final String MOD_NAME = "FerdieClient";
    public static Logger LOGGER = LogManager.getLogger();

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        log(Level.INFO, "Loading the commands");
        CommandRegistry.init();
        log(Level.INFO, "Loading the modules!");
        ModuleReg.init();
        KeybindReg.init();
        //TODO: Initializer
    }

}