package me.tl0x.ferdieclient;

import me.tl0x.ferdieclient.base.reg.CommandReg;
import me.tl0x.ferdieclient.base.reg.ModuleReg;
import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FerdieClient implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "ferdieclient";
    public static final String MOD_NAME = "FerdieClient1.18.1";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        CommandReg.init();
        log(Level.INFO, "Initializing Commands");

        ModuleReg.init();
        log(Level.INFO, "Initializing Modules");
        //TODO: Initializer
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}