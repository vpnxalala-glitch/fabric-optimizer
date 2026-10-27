package com.example.optimizer.features;

import net.minecraft.client.MinecraftClient;

public class ZoomFeature {
    private static boolean isZooming = false;
    private static double originalFov = 70.0; // Стандартный FOV

    public static void setZoom(boolean zoom) {
        if (isZooming == zoom) return;
        isZooming = zoom;
        
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options != null) {
            if (zoom) {
                // Запоминаем текущий FOV и ставим маленький (приближение)
                originalFov = client.options.getFov().getValue();
                client.options.getFov().setValue(30);
            } else {
                // Возвращаем как было
                client.options.getFov().setValue((int) originalFov);
            }
        }
    }
}
