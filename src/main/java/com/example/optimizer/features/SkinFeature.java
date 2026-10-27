package com.example.optimizer.features;

import net.fabricmc.loader.api.FabricLoader;
import javax.imageio.ImageIO;
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

            File zipFile = new File(rpDir, "BlackSkins.zip");
            if (zipFile.exists()) return; // Если уже есть, не создаем

            // Создаем нормальный черный скин 64x64
            BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    img.setRGB(x, y, 0xFF000000); // Непрозрачный черный
                }
            }

            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
                zos.putNextEntry(new ZipEntry("pack.mcmeta"));
                String meta = "{\n  \"pack\": {\n    \"pack_format\": 46,\n    \"description\": \"Black Skins\"\n  }\n}";
                zos.write(meta.getBytes());
                zos.closeEntry();

                zos.putNextEntry(new ZipEntry("assets/minecraft/textures/entity/player/wide/steve.png"));
                ImageIO.write(img, "png", zos);
                zos.closeEntry();
                
                zos.putNextEntry(new ZipEntry("assets/minecraft/textures/entity/player/slim/alex.png"));
                ImageIO.write(img, "png", zos);
                zos.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
