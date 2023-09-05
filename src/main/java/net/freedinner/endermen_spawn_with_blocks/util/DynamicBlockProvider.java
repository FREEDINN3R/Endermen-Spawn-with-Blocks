package net.freedinner.endermen_spawn_with_blocks.util;

import net.freedinner.endermen_spawn_with_blocks.EndermenSpawnWithBlocks;
import net.minecraft.block.Block;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public final class DynamicBlockProvider {
    private final Map<Identifier, TagKey<Block>> tagMap = new LinkedHashMap<>();
    private final Random random = new Random();

    private static final DynamicBlockProvider INSTANCE = new DynamicBlockProvider();

    private DynamicBlockProvider() {}

    public static DynamicBlockProvider getInstance() {
        return INSTANCE;
    }

    public void process(@NotNull MinecraftServer server) {
        this.tagMap.clear();
        for (ServerWorld world : server.getWorlds()) {
            Identifier id = world.getRegistryKey().getValue();
            String tagPath = String.format("%s/%s/blocks", id.getNamespace(), id.getPath());
            this.tagMap.put(id, TagKey.of(RegistryKeys.BLOCK, EndermenSpawnWithBlocks.id(tagPath)));
        }
    }

    public @Nullable Block getBlock(@NotNull ServerWorld world) {
        DynamicRegistryManager registry = world.getServer().getRegistryManager();
        Identifier id = world.getRegistryKey().getValue();
        if (!this.tagMap.containsKey(id)) return null;
        TagKey<Block> key = this.tagMap.get(id);
        Optional<RegistryEntryList.Named<Block>> named = registry.get(RegistryKeys.BLOCK).getEntryList(key);
        if (named.isEmpty()) return null;
        RegistryEntryList.Named<Block> tag = named.get();
        int index = this.random.nextInt(tag.size());
        Optional<RegistryKey<Block>> opt = tag.get(index).getKey();
        return opt.map(blockRegistryKey -> registry.get(RegistryKeys.BLOCK).get(blockRegistryKey)).orElse(null);
    }
}
