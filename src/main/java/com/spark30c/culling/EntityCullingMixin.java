package com.spark30c.culling;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityCullingMixin {

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void spark30c$shouldRender(Camera camera, CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        Entity entity = (Entity)(Object)this;
        double distance = client.player.distanceTo(entity);
        Box box = entity.getBoundingBox();

        // Rule 1: 24 block se door + camera me nahi = skip render. GPU bachao
        if (distance > 24.0 &&!camera.isInFrustum(box)) {
            cir.setReturnValue(false);
            return;
        }

        // Rule 2: Item/Particle 16 block se door = skip
        if (distance > 16.0 && (entity.getType().toString().contains("item") || entity.getType().toString().contains("particle"))) {
            cir.setReturnValue(false);
        }
    }
}
