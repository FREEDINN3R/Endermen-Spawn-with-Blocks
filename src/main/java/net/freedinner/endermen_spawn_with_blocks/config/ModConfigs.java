package net.freedinner.endermen_spawn_with_blocks.config;

import net.freedinner.endermen_spawn_with_blocks.EndermenSpawnWithBlocks;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;

public class ModConfigs {
    public static SimpleConfig CONFIG;

    public static double BLOCK_SPAWN_CHANCE;
    private static final String BLOCK_SPAWN_CHANCE_KEY = "block_spawn_chance";
    private static final double BLOCK_SPAWN_CHANCE_DEFAULT = 0.3 ;

    public static ArrayList<String> BLACKLISTED_DIMENSIONS;
    private static final String BLACKLISTED_DIMENSIONS_KEY = "blacklisted_dimensions";
    private static final String BLACKLISTED_DIMENSIONS_DEFAULT = "[ 'minecraft:the_end' ]";

    public static ArrayList<String> BLACKLISTED_BLOCKS;
    private static final String BLACKLISTED_BLOCKS_KEY = "blacklisted_blocks";
    private static final String BLACKLISTED_BLOCKS_DEFAULT = "[ 'minecraft:air', 'minecraft:another_block' ]";

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
        
        configProvider.addComment("Dimensions where Endermen can't place blocks");
        configProvider.addComment("The End is excluded by default due to the amount of Endermen spawning in it");
        configProvider.addField(BLACKLISTED_DIMENSIONS_KEY, BLACKLISTED_DIMENSIONS_DEFAULT);
        configProvider.addComment("");

        configProvider.addComment("Blocks Endermen shouldn't spawn with");
        configProvider.addComment("Putting a block which Endermen don't spawn with won't change anything");
        configProvider.addField(BLACKLISTED_BLOCKS_KEY, BLACKLISTED_BLOCKS_DEFAULT);
    }

    private static void assignConfigs() {
        BLOCK_SPAWN_CHANCE = MathHelper.clamp(
                CONFIG.getOrDefault(BLOCK_SPAWN_CHANCE_KEY, BLOCK_SPAWN_CHANCE_DEFAULT), 0.0, 1.0
        );

        BLACKLISTED_DIMENSIONS = configArrayToList(
                CONFIG.getOrDefault(BLACKLISTED_DIMENSIONS_KEY, BLACKLISTED_DIMENSIONS_DEFAULT)
        );

        BLACKLISTED_BLOCKS = configArrayToList(
                CONFIG.getOrDefault(BLACKLISTED_BLOCKS_KEY, BLACKLISTED_BLOCKS_DEFAULT)
        );
    }

    private static ArrayList<String> configArrayToList(String s) {
        ArrayList<String> list = new ArrayList<>();

        int searchPos = 0;
        while (searchPos < s.length() && s.indexOf("'", searchPos) != -1) {
            int startPos = s.indexOf("'", searchPos) + 1;
            int endPos = s.indexOf("'", startPos);

            if (endPos == -1) {
                break;
            }

            list.add(s.substring(startPos, endPos));
            searchPos = endPos + 1;
        }

        return list;
    }
}