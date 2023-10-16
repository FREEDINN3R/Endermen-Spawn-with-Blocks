package net.freedinner.endermen_spawn_with_blocks.util;

import net.freedinner.endermen_spawn_with_blocks.EndermenSpawnWithBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.world.World;

public final class ConfigTagsProvider {
    private ConfigTagsProvider() {}

    public static final TagKey<Block> BLOCK_BLACKLIST = register(RegistryKeys.BLOCK, "blacklist");

    public static final TagKey<World> WORLD_BLACKLIST = register(RegistryKeys.WORLD, "blacklist");

    public static <T> TagKey<T> register(RegistryKey<Registry<T>> key, String path) {
        return TagKey.of(key, EndermenSpawnWithBlocks.id(path));
    }
}
