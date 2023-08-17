package net.freedinner.endermen_spawn_with_blocks.util;

import net.freedinner.endermen_spawn_with_blocks.config.ModConfigs;
import net.minecraft.block.Blocks;
import net.minecraft.util.Pair;

public class EndBlockProvider extends AbstractBlockProvider {
    @Override
    protected void populateBlocksList() {
        tryAddBlock(Blocks.COARSE_DIRT, 5);
        tryAddBlock(Blocks.ROOTED_DIRT, 5);
        tryAddBlock(Blocks.DRIPSTONE_BLOCK, 5);
        tryAddBlock(Blocks.SAND, 5);
        tryAddBlock(Blocks.RED_SAND, 5);
        tryAddBlock(Blocks.GRAVEL, 5);

        tryAddBlock(Blocks.CHISELED_STONE_BRICKS, 4);
        tryAddBlock(Blocks.CHISELED_SANDSTONE, 4);
        tryAddBlock(Blocks.CHISELED_RED_SANDSTONE, 4);
        tryAddBlock(Blocks.CHISELED_NETHER_BRICKS, 4);
        tryAddBlock(Blocks.CARVED_PUMPKIN, 4);
        tryAddBlock(Blocks.MELON, 4);

        tryAddBlock(Blocks.DEEPSLATE_COAL_ORE, 3);
        tryAddBlock(Blocks.TNT, 3);
        tryAddBlock(Blocks.HAY_BLOCK, 3);
        tryAddBlock(Blocks.CAKE, 3);
        tryAddBlock(Blocks.DRIED_KELP_BLOCK, 3);

        tryAddBlock(Blocks.DEEPSLATE_DIAMOND_ORE, 2);
        tryAddBlock(Blocks.DEEPSLATE_DIAMOND_ORE, 2);
        tryAddBlock(Blocks.DAMAGED_ANVIL, 2);
        tryAddBlock(Blocks.HONEYCOMB_BLOCK, 2);
        tryAddBlock(Blocks.BEE_NEST, 2);
        tryAddBlock(Blocks.SPONGE, 2);

        tryAddBlock(Blocks.DIAMOND_BLOCK, 1);
        tryAddBlock(Blocks.EMERALD_BLOCK, 1);
        tryAddBlock(Blocks.ENCHANTING_TABLE, 1);
        tryAddBlock(Blocks.JUKEBOX, 1);
    }

    @Override
    public boolean shouldHoldBlock() {
        return !isBlockListEmpty() && Math.random() < (ModConfigs.BLOCK_SPAWN_CHANCE / 3);
    }
}
