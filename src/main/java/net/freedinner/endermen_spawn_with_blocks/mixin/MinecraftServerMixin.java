package net.freedinner.endermen_spawn_with_blocks.mixin;

import net.freedinner.endermen_spawn_with_blocks.util.DynamicBlockProvider;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Mixin(MinecraftServer.class)
abstract class MinecraftServerMixin {

    @Inject(method = "reloadResources", at = @At(value = "INVOKE", shift = At.Shift.AFTER, ordinal = 0, target = "Ljava/util/concurrent/CompletableFuture;supplyAsync(Ljava/util/function/Supplier;Ljava/util/concurrent/Executor;)Ljava/util/concurrent/CompletableFuture;"))
    private void loadDatapackAfterFutureSupplyAsyncInjection(Collection<String> dataPacks, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        cir.getReturnValue().thenRun(() -> DynamicBlockProvider.getInstance().process((MinecraftServer) ((Object)this)));
    }
}
