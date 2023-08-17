package net.freedinner.endermen_spawn_with_blocks.util;

import net.freedinner.endermen_spawn_with_blocks.config.ModConfigs;
import net.minecraft.block.Blocks;

public class OverworldBlockProvider extends AbstractBlockProvider {
    @Override
    protected void populateBlocksList() {
        tryAddBlock(Blocks.NETHERRACK, 5);
        tryAddBlock(Blocks.CRIMSON_NYLIUM, 5);
        tryAddBlock(Blocks.WARPED_NYLIUM, 5);
        tryAddBlock(Blocks.BLACKSTONE, 5);
        tryAddBlock(Blocks.BASALT, 5);
        tryAddBlock(Blocks.ORANGE_WOOL, 5);

        tryAddBlock(Blocks.CRIMSON_STEM, 4);
        tryAddBlock(Blocks.WARPED_STEM, 4);
        tryAddBlock(Blocks.NETHER_WART_BLOCK, 4);
        tryAddBlock(Blocks.WARPED_WART_BLOCK, 4);
        tryAddBlock(Blocks.NETHER_BRICKS, 4);
        tryAddBlock(Blocks.RED_NETHER_BRICKS, 4);

        tryAddBlock(Blocks.SOUL_SAND, 3);
        tryAddBlock(Blocks.SOUL_SOIL, 3);
        tryAddBlock(Blocks.BONE_BLOCK, 3);
        tryAddBlock(Blocks.MAGMA_BLOCK, 3);

        tryAddBlock(Blocks.NETHER_QUARTZ_ORE, 2);
        tryAddBlock(Blocks.NETHER_GOLD_ORE, 2);
        tryAddBlock(Blocks.GLOWSTONE, 2);
        tryAddBlock(Blocks.SHROOMLIGHT, 2);
        tryAddBlock(Blocks.CRYING_OBSIDIAN, 2);

        tryAddBlock(Blocks.ANCIENT_DEBRIS, 1);
        tryAddBlock(Blocks.GOLD_BLOCK, 1);
        tryAddBlock(Blocks.LODESTONE, 1);
        tryAddBlock(Blocks.GILDED_BLACKSTONE, 1);
    }

    @Override
    public boolean shouldHoldBlock() {
        return !isBlockListEmpty() && Math.random() < ModConfigs.BLOCK_SPAWN_CHANCE;
    }
}
