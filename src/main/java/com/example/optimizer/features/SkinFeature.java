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
                zos.putNextEntry(new ZipEntry("pack.mcmeta"));
                String meta = "{\n  \"pack\": {\n    \"pack_format\": 46,\n    \"description\": \"Simple Visuals\"\n  }\n}";
                zos.write(meta.getBytes());
                zos.closeEntry();

                // Черные скины
                addTexture(zos, "assets/minecraft/textures/entity/player/wide/steve.png", Color.BLACK, 64, 64);
                addTexture(zos, "assets/minecraft/textures/entity/player/slim/alex.png", Color.BLACK, 64, 64);

                // Плоская броня
                addTexture(zos, "assets/minecraft/textures/models/armor/diamond_layer_1.png", new Color(0x00BFFF), 64, 32);
                addTexture(zos, "assets/minecraft/textures/models/armor/iron_layer_1.png", new Color(0xD3D3D3), 64, 32);
                addTexture(zos, "assets/minecraft/textures/models/armor/gold_layer_1.png", new Color(0xFFD700), 64, 32);
                addTexture(zos, "assets/minecraft/textures/models/armor/netherite_layer_1.png", new Color(0x2F4F4F), 64, 32);
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
