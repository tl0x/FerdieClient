package me.tl0x.FerdieClient;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FerdieClient implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "FC";
    public static final String MOD_NAME = "FerdieClient";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        log(Level.INFO, "Hello World!");
        //TODO: Initializer
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}