package net.freedinner.endermen_spawn_with_blocks.util;

import net.freedinner.endermen_spawn_with_blocks.EndermenSpawnWithBlocks;
import net.minecraft.block.Block;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.util.registry.RegistryKey;
import org.jetbrains.annotations.NotNull;

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
            this.tagMap.put(id, TagKey.of(Registry.BLOCK_KEY, new Identifier(EndermenSpawnWithBlocks.MOD_ID, tagPath)));
        }
    }

    public Block getBlock(@NotNull ServerWorld world) {
        DynamicRegistryManager registry = world.getServer().getRegistryManager();
        Identifier id = world.getRegistryKey().getValue();
        if (!this.tagMap.containsKey(id)) return null;
        TagKey<Block> key = this.tagMap.get(id);
        Optional<RegistryEntryList.Named<Block>> named = registry.get(Registry.BLOCK_KEY).getEntryList(key);
        if (named.isEmpty()) return null;
        RegistryEntryList.Named<Block> tag = named.get();
        int index = this.random.nextInt(tag.size());
        Optional<RegistryKey<Block>> opt = tag.get(index).getKey();
        return opt.map(blockRegistryKey -> registry.get(Registry.BLOCK_KEY).get(blockRegistryKey)).orElse(null);
    }
}
