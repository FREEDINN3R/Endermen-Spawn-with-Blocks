package net.freedinner.endermen_spawn_with_blocks.mixin;

import net.freedinner.endermen_spawn_with_blocks.util.DynamicBlockProvider;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.concurrent.CompletableFuture;

@Mixin(MinecraftServer.class)
abstract class MinecraftServerMixin {

    @ModifyVariable(method = "reloadResources", at = @At(value = "INVOKE", target = "Ljava/util/concurrent/CompletableFuture;thenAcceptAsync(Ljava/util/function/Consumer;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;", shift = At.Shift.AFTER))
    private CompletableFuture<Void> loadDatapackAfterFutureInjection(CompletableFuture<Void> completableFuture) {
        return completableFuture.thenRun(() -> {
            DynamicBlockProvider.getInstance().process((MinecraftServer)((Object) this));
        });
    }
}
