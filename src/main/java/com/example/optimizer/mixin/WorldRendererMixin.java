package com.example.optimizer.mixin;

import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    // Этот миксин нужен, чтобы игра не рендерила лишнюю графику
    // В реальности, чтобы убрать листву, нужно перехватывать метод renderBlock
    // Но пока оставим заготовку, чтобы мод собрался
}
