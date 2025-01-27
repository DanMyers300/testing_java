package net.danmyers.testing_java;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestingJava implements ModInitializer {
    public static final String MOD_ID = "testing_java";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.initialize();
        LOGGER.info("Testing Java mod initialized!");
    }
}
