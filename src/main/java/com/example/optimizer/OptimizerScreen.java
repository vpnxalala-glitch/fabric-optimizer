package com.example.optimizer;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode; // Правильный класс для 1.21.4

public class OptimizerScreen extends Screen {
    public OptimizerScreen() {
        super(Text.literal("Меню Оптимизации"));
    }

    @Override
    protected void init() {
        MinecraftClient client = MinecraftClient.getInstance();

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Дальность: 6 чанков"), button -> {
            client.options.getViewDistance().setValue(6);
        }).dimensions(this.width / 2 - 100, this.height / 2 - 60, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Частицы: Выкл"), button -> {
            // Используем ParticlesMode.MINIMAL
            client.options.getParticles().setValue(ParticlesMode.MINIMAL); 
        }).dimensions(this.width / 2 - 100, this.height / 2 - 30, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Графика: Быстрая"), button -> {
            // В 1.21.4 GraphicsMode остался в net.minecraft.client.option
            client.options.getGraphicsMode().setValue(net.minecraft.client.option.GraphicsMode.FAST);
        }).dimensions(this.width / 2 - 100, this.height / 2, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Закрыть"), button -> {
            this.close();
        }).dimensions(this.width / 2 - 100, this.height / 2 + 30, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
