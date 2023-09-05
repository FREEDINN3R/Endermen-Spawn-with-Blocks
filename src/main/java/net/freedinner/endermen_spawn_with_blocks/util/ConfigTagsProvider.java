package net.freedinner.endermen_spawn_with_blocks.util;

import net.freedinner.endermen_spawn_with_blocks.EndermenSpawnWithBlocks;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public final class ConfigTagsProvider {
    private ConfigTagsProvider() {}

    public static TagKey<Block> BLOCK_BLACKLIST = register(Registry.BLOCK_KEY, "blacklist");

    public static TagKey<World> WORLD_BLACKLIST = register(Registry.WORLD_KEY, "blacklist");

    public static <T> TagKey<T> register(RegistryKey<Registry<T>> key, String path) {
        return TagKey.of(key, EndermenSpawnWithBlocks.id(path));
    }
}
