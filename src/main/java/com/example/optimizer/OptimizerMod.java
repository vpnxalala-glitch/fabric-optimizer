package com.example.optimizer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import com.example.optimizer.gui.OptimizerScreen;
import com.example.optimizer.config.OptimizerConfig;
import com.example.optimizer.features.FullbrightFeature;
import com.example.optimizer.features.SkinFeature;
import net.minecraft.client.MinecraftClient;

public class OptimizerMod implements ClientModInitializer {
    public static KeyBinding menuKey;
    public static KeyBinding fullbrightKey;
    public static OptimizerConfig config;

    @Override
    public void onInitializeClient() {
        config = OptimizerConfig.load();
        SkinFeature.createResourcePack();

        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.optimizer.menu", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.optimizer"
        ));
        
        fullbrightKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.optimizer.fullbright", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "category.optimizer"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKey.wasPressed()) {
                if (client.player != null) client.setScreen(new OptimizerScreen());
            }
            while (fullbrightKey.wasPressed()) {
                FullbrightFeature.toggle();
            }
            
            // Принудительный фуллбрайт, если включен
            if (config.fullbrightEnabled && client.options != null) {
                if (client.options.getGamma().getValue() < 9.0) {
                    client.options.getGamma().setValue(10.0);
                }
            }
        });
    }
}
