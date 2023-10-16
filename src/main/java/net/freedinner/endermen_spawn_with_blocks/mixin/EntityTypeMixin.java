package net.freedinner.endermen_spawn_with_blocks.mixin;

import net.freedinner.endermen_spawn_with_blocks.util.*;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public abstract class EntityTypeMixin<T extends Entity> {
    @Inject(method = "create(Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;", at = @At("RETURN"))
    private void onCreate(World w, @NotNull CallbackInfoReturnable<T> cir) {
        if (cir.getReturnValue() instanceof EndermanEntity enderman && w instanceof ServerWorld world) {

            Block b = DynamicBlockProvider.getInstance().getBlock(world);

            if (b == null || b.getRegistryEntry().isIn(ConfigTagsProvider.BLOCK_BLACKLIST)) {
                return;
            }

            enderman.setCarriedBlock(b.getDefaultState());
        }
    }
}
