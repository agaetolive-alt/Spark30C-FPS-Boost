package com.spark30c;

import net.fabricmc.api.ModInitializer;
import com.spark30c.memory.RamCleaner;

public class SparkBoostMod implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("[Spark30C] Mod Loaded - Optimizing Mali-G57");
        RamCleaner.start(); // RAM cleaner auto start
    }
}
