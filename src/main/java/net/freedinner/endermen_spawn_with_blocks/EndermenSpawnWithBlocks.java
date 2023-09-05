package net.freedinner.endermen_spawn_with_blocks;

import net.fabricmc.api.ModInitializer;
import net.freedinner.endermen_spawn_with_blocks.config.ModConfigs;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndermenSpawnWithBlocks implements ModInitializer {
	public static final String MOD_ID = "endermen_spawn_with_blocks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();
	}

	@Contract("_ -> new")
	public static @NotNull Identifier id(@NotNull String path) {
		return new Identifier(MOD_ID, path);
	}
}