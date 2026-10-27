package com.example.optimizer.features;

import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourcePackManager;
import java.io.File;

public class SkinFeature {
    public static void applyBlackSkins() {
        // В реальности тут нужно создавать zip с черными скинами
        // Пока это просто заготовка
        MinecraftClient client = MinecraftClient.getInstance();
        // client.getResourcePackManager().scanPacks();
    }
}
