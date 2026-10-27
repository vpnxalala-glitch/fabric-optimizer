package com.example.optimizer.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class OptimizerScreen extends Screen {
    public OptimizerScreen() {
        super(Text.literal("Меню Оптимизации"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, this.height / 2, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
