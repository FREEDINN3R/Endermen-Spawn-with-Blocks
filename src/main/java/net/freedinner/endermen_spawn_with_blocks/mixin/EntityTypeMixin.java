package net.freedinner.endermen_spawn_with_blocks.mixin;

import net.freedinner.endermen_spawn_with_blocks.util.AbstractBlockProvider;
import net.freedinner.endermen_spawn_with_blocks.util.EndBlockProvider;
import net.freedinner.endermen_spawn_with_blocks.util.NetherBlockProvider;
import net.freedinner.endermen_spawn_with_blocks.util.OverworldBlockProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityType.class)
public abstract class EntityTypeMixin<T extends Entity> {
    @Inject(method = "create(Lnet/minecraft/world/World;)Lnet/minecraft/entity/Entity;", at = @At("RETURN"))
    private void onCreate(World world, CallbackInfoReturnable<T> cir) {
        if (cir.getReturnValue() instanceof EndermanEntity enderman) {
            if (world.isClient) {
                return;
            }

            AbstractBlockProvider blockProvider = switch (world.getRegistryKey().getValue().toString()) {
                case "minecraft:overworld" -> new OverworldBlockProvider();
                case "minecraft:the_nether" -> new NetherBlockProvider();
                default -> new EndBlockProvider();
            };

            if (!blockProvider.shouldHoldBlock()) {
                return;
            }

            enderman.setCarriedBlock(blockProvider.getBlock().getDefaultState());
        }
    }
}
