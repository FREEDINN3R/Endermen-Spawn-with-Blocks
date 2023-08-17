package net.freedinner.endermen_spawn_with_blocks.util;

import net.freedinner.endermen_spawn_with_blocks.EndermenSpawnWithBlocks;
import net.freedinner.endermen_spawn_with_blocks.config.ModConfigs;
import net.minecraft.block.Block;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBlockProvider {
    private final List<Pair<Block, Integer>> blocks;
    private int totalWeight;

    public AbstractBlockProvider() {
        blocks = new ArrayList<>();
        populateBlocksList();

        totalWeight = 0;
        for (Pair<Block, Integer> pair : blocks) {
            totalWeight += pair.getRight();
        }
    }

    protected abstract void populateBlocksList();

    public abstract boolean shouldHoldBlock();

    protected void tryAddBlock(Block block, int weight) {
        String blockName = block.getRegistryEntry().getKey().get().getValue().toString();
        if (!ModConfigs.BLACKLISTED_BLOCKS.contains(blockName)) {
            blocks.add(new Pair<>(block, weight));
        }
    }

    protected boolean isBlockListEmpty() {
        return blocks.isEmpty();
    }

    public Block getBlock() {
        double r = Math.random() * totalWeight;
        int i;

        for (i = 0; i < blocks.size() - 1; i++) {
            r -= blocks.get(i).getRight();
            if (r <= 0.0) break;
        }

        return blocks.get(i).getLeft();
    }
}
