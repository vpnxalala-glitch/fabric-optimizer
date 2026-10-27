package com.example.optimizer.features;

import net.fabricmc.loader.api.FabricLoader;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SkinFeature {
    public static void createResourcePack() {
        try {
            File gameDir = FabricLoader.getInstance().getGameDir().toFile();
            File rpDir = new File(gameDir, "resourcepacks");
            if (!rpDir.exists()) rpDir.mkdirs();

            File zipFile = new File(rpDir, "SimpleVisuals.zip");
            if (zipFile.exists()) zipFile.delete();

            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
                // pack.mcmeta (формат 46 для 1.21.4)
                zos.putNextEntry(new ZipEntry("pack.mcmeta"));
                String meta = "{\n  \"pack\": {\n    \"pack_format\": 46,\n    \"description\": \"Simple Visuals\"\n  }\n}";
                zos.write(meta.getBytes());
                zos.closeEntry();

                // --- СКИНЫ ---
                addTexture(zos, "assets/minecraft/textures/entity/player/wide/steve.png", Color.BLACK, 64, 64);
                addTexture(zos, "assets/minecraft/textures/entity/player/slim/alex.png", Color.BLACK, 64, 64);

                // --- БРОНЯ (Плоские цвета, формат 64x32) ---
                // Алмаз (Синий)
                addTexture(zos, "assets/minecraft/textures/models/armor/diamond_layer_1.png", new Color(0x0055FF), 64, 32);
                addTexture(zos, "assets/minecraft/textures/models/armor/diamond_layer_2.png", new Color(0x0077FF), 64, 32);
                // Железо (Серый)
                addTexture(zos, "assets/minecraft/textures/models/armor/iron_layer_1.png", new Color(0xAAAAAA), 64, 32);
                addTexture(zos, "assets/minecraft/textures/models/armor/iron_layer_2.png", new Color(0xCCCCCC), 64, 32);
                // Золото (Желтый)
                addTexture(zos, "assets/minecraft/textures/models/armor/gold_layer_1.png", new Color(0xFFD700), 64, 32);
                addTexture(zos, "assets/minecraft/textures/models/armor/gold_layer_2.png", new Color(0xFFAA00), 64, 32);
                // Незерит (Темный)
                addTexture(zos, "assets/minecraft/textures/models/armor/netherite_layer_1.png", new Color(0x333333), 64, 32);
                addTexture(zos, "assets/minecraft/textures/models/armor/netherite_layer_2.png", new Color(0x111111), 64, 32);
                // Кожа (Коричневый)
                addTexture(zos, "assets/minecraft/textures/models/armor/leather_layer_1.png", new Color(0x8B4513), 64, 32);
                addTexture(zos, "assets/minecraft/textures/models/armor/leather_layer_2.png", new Color(0x5C2E0B), 64, 32);

                // --- ИНСТРУМЕНТЫ (Плоские цвета, 16x16) ---
                // Алмаз
                addTexture(zos, "assets/minecraft/textures/item/diamond_sword.png", new Color(0x0055FF), 16, 16);
                addTexture(zos, "assets/minecraft/textures/item/diamond_pickaxe.png", new Color(0x0077FF), 16, 16);
                addTexture(zos, "assets/minecraft/textures/item/diamond_axe.png", new Color(0x00AAFF), 16, 16);
                addTexture(zos, "assets/minecraft/textures/item/diamond_shovel.png", new Color(0x00DDFF), 16, 16);
                addTexture(zos, "assets/minecraft/textures/item/diamond_hoe.png", new Color(0x0055FF), 16, 16);
                
                // Железо
                addTexture(zos, "assets/minecraft/textures/item/iron_sword.png", new Color(0xAAAAAA), 16, 16);
                addTexture(zos, "assets/minecraft/textures/item/iron_pickaxe.png", new Color(0xCCCCCC), 16, 16);
                addTexture(zos, "assets/minecraft/textures/item/iron_axe.png", new Color(0xBBBBBB), 16, 16);
                addTexture(zos, "assets/minecraft/textures/item/iron_shovel.png", new Color(0xDDDDDD), 16, 16);
                
                // Золото
                addTexture(zos, "assets/minecraft/textures/item/gold_sword.png", new Color(0xFFD700), 16, 16);
                addTexture(zos, "assets/minecraft/textures/item/gold_pickaxe.png", new Color(0xFFAA00), 16, 16);
                addTexture(zos, "assets/minecraft/textures/item/gold_axe.png", new Color(0xFFCC00), 16, 16);
                
                // Незерит
                addTexture(zos, "assets/minecraft/textures/item/netherite_sword.png", new Color(0x333333), 16, 16);
                addTexture(zos, "assets/minecraft/textures/item/netherite_pickaxe.png", new Color(0x444444), 16, 16);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTexture(ZipOutputStream zos, String path, Color color, int width, int height) throws Exception {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(color);
        g.fillRect(0, 0, width, height);
        g.dispose();

        zos.putNextEntry(new ZipEntry(path));
        ImageIO.write(img, "png", zos);
        zos.closeEntry();
    }
}
