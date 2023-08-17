package net.freedinner.endermen_spawn_with_blocks.mixin;

import net.freedinner.endermen_spawn_with_blocks.config.ModConfigs;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PlaceBlockGoal")
public abstract class EndermanEntityMixin {
    @Inject(method = "canPlaceOn", at = @At("RETURN"), cancellable = true)
    private void canPlaceOnInjected(World world, BlockPos posAbove, BlockState carriedState, BlockState stateAbove, BlockState state, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        String currDimension = world.getRegistryKey().getValue().toString();
        boolean allowed = !ModConfigs.BLACKLISTED_DIMENSIONS.contains(currDimension);
        cir.setReturnValue(allowed && cir.getReturnValue());
    }
}
