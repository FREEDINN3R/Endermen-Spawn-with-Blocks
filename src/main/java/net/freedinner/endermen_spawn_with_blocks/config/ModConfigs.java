package net.freedinner.endermen_spawn_with_blocks.config;

import net.freedinner.endermen_spawn_with_blocks.EndermenSpawnWithBlocks;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;

public class ModConfigs {
    public static SimpleConfig CONFIG;

    public static double BLOCK_SPAWN_CHANCE;
    private static final String BLOCK_SPAWN_CHANCE_KEY = "block_spawn_chance";
    private static final double BLOCK_SPAWN_CHANCE_DEFAULT = 0.3 ;


    public static void registerConfigs() {
        EndermenSpawnWithBlocks.LOGGER.info("Registering configs");

        ModConfigProvider configProvider = new ModConfigProvider();
        initializeConfig(configProvider);

        CONFIG = SimpleConfig.of(EndermenSpawnWithBlocks.MOD_ID + "_config").provider(configProvider).request();
        assignConfigs();
    }

    private static void initializeConfig(ModConfigProvider configProvider) {
        configProvider.addComment("The chance that an Enderman will be holding a block on spawn");
        configProvider.addComment("Note that this chance is decreased for The End and all modded dimensions");
        configProvider.addComment("Min = 0.0, max = 1.0");
        configProvider.addField(BLOCK_SPAWN_CHANCE_KEY, BLOCK_SPAWN_CHANCE_DEFAULT);
        configProvider.addComment("");
    }

    private static void assignConfigs() {
        BLOCK_SPAWN_CHANCE = MathHelper.clamp(
                CONFIG.getOrDefault(BLOCK_SPAWN_CHANCE_KEY, BLOCK_SPAWN_CHANCE_DEFAULT), 0.0, 1.0
        );
    }
}