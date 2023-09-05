package net.freedinner.endermen_spawn_with_blocks;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndermenSpawnWithBlocks {
	public static final String MOD_ID = "endermen_spawn_with_blocks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Contract("_ -> new")
	public static @NotNull Identifier id(@NotNull String path) {
		return new Identifier(MOD_ID, path);
	}
}