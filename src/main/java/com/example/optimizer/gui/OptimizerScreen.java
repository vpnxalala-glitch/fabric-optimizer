package com.example.optimizer.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticlesMode;

public class OptimizerScreen extends Screen {
    public OptimizerScreen() {
        super(Text.literal("Оптимизатор"));
    }

    @Override
    protected void init() {
        MinecraftClient client = MinecraftClient.getInstance();
        this.clearChildren();
        int startY = this.height / 2 - 20;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Включить ВСЁ (Макс. FPS)"), b -> {
            client.options.getViewDistance().setValue(2);
            client.options.getSimulationDistance().setValue(2);
            client.options.getParticles().setValue(ParticlesMode.MINIMAL);
            client.options.getGraphicsMode().setValue(net.minecraft.client.option.GraphicsMode.FAST);
            client.options.getCloudRenderMode().setValue(net.minecraft.client.option.CloudRenderMode.OFF);
            client.options.write();
            b.setMessage(Text.literal("Всё включено!"));
        }).dimensions(this.width / 2 - 100, startY, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Закрыть"), b -> this.close())
            .dimensions(this.width / 2 - 100, startY + 50, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
