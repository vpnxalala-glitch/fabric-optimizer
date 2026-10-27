package com.example.optimizer.features;

import net.minecraft.client.MinecraftClient;
import com.example.optimizer.OptimizerMod;

public class FullbrightFeature {
    public static void init() {
        if (OptimizerMod.config.fullbrightEnabled) {
            setGamma(10.0);
        }
    }

    public static void toggle() {
        OptimizerMod.config.fullbrightEnabled = !OptimizerMod.config.fullbrightEnabled;
        setGamma(OptimizerMod.config.fullbrightEnabled ? 10.0 : 1.0);
        OptimizerMod.config.save();
    }

    private static void setGamma(double gamma) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options != null) {
            client.options.getGamma().setValue(gamma);
        }
    }
}
