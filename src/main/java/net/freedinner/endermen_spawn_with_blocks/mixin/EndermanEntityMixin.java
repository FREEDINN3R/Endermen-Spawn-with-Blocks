package net.freedinner.endermen_spawn_with_blocks.mixin;

import net.freedinner.endermen_spawn_with_blocks.config.ModConfigs;
import net.freedinner.endermen_spawn_with_blocks.util.ConfigTagsProvider;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PlaceBlockGoal")
public abstract class EndermanEntityMixin {
    @Inject(method = "canPlaceOn", at = @At("RETURN"), cancellable = true)
    private void canPlaceOnInjected(@NotNull World world, BlockPos posAbove, BlockState carriedState,
                                    BlockState stateAbove, BlockState state, BlockPos pos,
                                    @NotNull CallbackInfoReturnable<Boolean> cir) {
        RegistryKey<World> key = world.getRegistryKey();
        DynamicRegistryManager manager = world.getServer().getRegistryManager();
        boolean allowed = !manager.get(Registry.WORLD_KEY).entryOf(key).isIn(ConfigTagsProvider.WORLD_BLACKLIST);
        cir.setReturnValue(allowed && cir.getReturnValue());
    }
}
