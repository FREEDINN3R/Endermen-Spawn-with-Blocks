package net.freedinner.endermen_spawn_with_blocks.util;

import net.freedinner.endermen_spawn_with_blocks.config.ModConfigs;
import net.minecraft.block.Blocks;
import net.minecraft.util.Pair;

public class NetherBlockProvider extends AbstractBlockProvider {
    @Override
    protected void populateBlocksList() {
        tryAddBlock(Blocks.END_STONE, 5);
        tryAddBlock(Blocks.END_STONE_BRICKS, 5);
        tryAddBlock(Blocks.END_STONE_BRICK_STAIRS, 5);
        tryAddBlock(Blocks.RED_WOOL, 5);
        tryAddBlock(Blocks.GREEN_WOOL, 5);
        tryAddBlock(Blocks.BLUE_WOOL, 5);

        tryAddBlock(Blocks.PURPUR_BLOCK, 4);
        tryAddBlock(Blocks.PURPUR_PILLAR, 4);
        tryAddBlock(Blocks.PURPUR_STAIRS, 4);
        tryAddBlock(Blocks.MAGENTA_STAINED_GLASS, 4);
        tryAddBlock(Blocks.CYAN_CONCRETE_POWDER, 4);
        tryAddBlock(Blocks.MAGENTA_CONCRETE_POWDER, 4);
        tryAddBlock(Blocks.YELLOW_CONCRETE_POWDER, 4);

        tryAddBlock(Blocks.OBSIDIAN, 3);
        tryAddBlock(Blocks.CHORUS_PLANT, 3);
        tryAddBlock(Blocks.CHORUS_FLOWER, 3);
        tryAddBlock(Blocks.PACKED_ICE, 3);
        tryAddBlock(Blocks.BLUE_ICE, 3);

        tryAddBlock(Blocks.PRISMARINE, 2);
        tryAddBlock(Blocks.PRISMARINE_BRICKS, 2);
        tryAddBlock(Blocks.DARK_PRISMARINE, 2);
        tryAddBlock(Blocks.SEA_LANTERN, 2);

        tryAddBlock(Blocks.SHULKER_BOX, 1);
        tryAddBlock(Blocks.ENDER_CHEST, 1);
        tryAddBlock(Blocks.OCHRE_FROGLIGHT, 1);
        tryAddBlock(Blocks.PEARLESCENT_FROGLIGHT, 1);
        tryAddBlock(Blocks.VERDANT_FROGLIGHT, 1);
    }

    @Override
    public boolean shouldHoldBlock() {
        return !isBlockListEmpty() && Math.random() < ModConfigs.BLOCK_SPAWN_CHANCE;
    }
}
