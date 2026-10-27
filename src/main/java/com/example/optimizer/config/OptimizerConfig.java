package com.example.optimizer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class OptimizerConfig {
    public boolean fullbrightEnabled = false;
    public int zoomFov = 30; // Настройка зума
    
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "optimizer.json");

    public static OptimizerConfig load() {
        if (FILE.exists()) {
            try (FileReader reader = new FileReader(FILE)) {
                return GSON.fromJson(reader, OptimizerConfig.class);
            } catch (Exception e) { e.printStackTrace(); }
        }
        return new OptimizerConfig();
    }

    public void save() {
        try (FileWriter writer = new FileWriter(FILE)) {
            GSON.toJson(this, writer);
        } catch (Exception e) { e.printStackTrace(); }
    }
}
