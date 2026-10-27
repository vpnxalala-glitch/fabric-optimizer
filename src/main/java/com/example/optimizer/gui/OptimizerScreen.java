package com.example.optimizer.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticlesMode;
import com.example.optimizer.OptimizerMod;
import com.example.optimizer.features.FullbrightFeature;

public class OptimizerScreen extends Screen {
    private int currentTab = 0; // 0 - Графика, 1 - Оптимизация

    public OptimizerScreen() {
        super(Text.literal("Оптимизатор Майнкрафта"));
    }

    @Override
    protected void init() {
        MinecraftClient client = MinecraftClient.getInstance();
        this.clearChildren();

        int startY = this.height / 2 - 60;

        // Вкладки
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Графика"), b -> { currentTab = 0; this.init(); })
            .dimensions(this.width / 2 - 150, startY - 30, 100, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Оптимизация"), b -> { currentTab = 1; this.init(); })
            .dimensions(this.width / 2 + 50, startY - 30, 100, 20).build());

        if (currentTab == 0) {
            // Вкладка Графика
            String fbText = OptimizerMod.config.fullbrightEnabled ? "Фуллбрайт: ВКЛ" : "Фуллбрайт: ВЫКЛ";
            this.addDrawableChild(ButtonWidget.builder(Text.literal(fbText), b -> {
                FullbrightFeature.toggle();
                b.setMessage(Text.literal(OptimizerMod.config.fullbrightEnabled ? "Фуллбрайт: ВКЛ" : "Фуллбрайт: ВЫКЛ"));
            }).dimensions(this.width / 2 - 100, startY, 200, 20).build());

            this.addDrawableChild(ButtonWidget.builder(Text.literal("Дальность: 6 чанков"), b -> {
                client.options.getViewDistance().setValue(6);
                b.setMessage(Text.literal("Дальность: 6"));
            }).dimensions(this.width / 2 - 100, startY + 30, 200, 20).build());

            this.addDrawableChild(ButtonWidget.builder(Text.literal("Графика: Быстрая"), b -> {
                client.options.getGraphicsMode().setValue(net.minecraft.client.option.GraphicsMode.FAST);
                b.setMessage(Text.literal("Графика: Быстрая"));
            }).dimensions(this.width / 2 - 100, startY + 60, 200, 20).build());
            
        } else {
            // Вкладка Оптимизация
            this.addDrawableChild(ButtonWidget.builder(Text.literal("Частицы: Минимум"), b -> {
                client.options.getParticles().setValue(ParticlesMode.MINIMAL);
                b.setMessage(Text.literal("Частицы: Мин"));
            }).dimensions(this.width / 2 - 100, startY, 200, 20).build());

            this.addDrawableChild(ButtonWidget.builder(Text.literal("Облака: Выкл"), b -> {
                client.options.getCloudRenderMode().setValue(net.minecraft.client.option.CloudRenderMode.OFF);
                b.setMessage(Text.literal("Облака: Выкл"));
            }).dimensions(this.width / 2 - 100, startY + 30, 200, 20).build());
        }

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Закрыть"), b -> this.close())
            .dimensions(this.width / 2 - 100, startY + 120, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
