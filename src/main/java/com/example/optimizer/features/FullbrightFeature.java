package com.example.optimizer.features;

import net.minecraft.client.MinecraftClient;
import com.example.optimizer.OptimizerMod;

public class FullbrightFeature {
    public static void toggle() {
        OptimizerMod.config.fullbrightEnabled = !OptimizerMod.config.fullbrightEnabled;
        OptimizerMod.config.save();
        
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options != null) {
            client.options.getGamma().setValue(OptimizerMod.config.fullbrightEnabled ? 10.0 : 1.0);
        }
    }
}
