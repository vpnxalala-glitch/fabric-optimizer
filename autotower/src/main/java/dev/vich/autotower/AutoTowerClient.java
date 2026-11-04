package dev.vich.autotower;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public final class AutoTowerClient implements ClientModInitializer {
    private static final KeyBinding.Category CATEGORY =
            KeyBinding.Category.create(Identifier.of("autotower", "controls"));

    private static KeyBinding toggleKey;
    private static boolean enabled = true;
    private static int lastPlacedY = Integer.MIN_VALUE;
    private static int placeCooldown = 0;

    @Override
    public void onInitializeClient() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.autotower.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                CATEGORY
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                enabled = !enabled;
                lastPlacedY = Integer.MIN_VALUE;
                if (client.player != null) {
                    client.player.sendMessage(
                            net.minecraft.text.Text.literal("Auto Tower: " + (enabled ? "ON" : "OFF")),
                            true
                    );
                }
            }

            if (placeCooldown > 0) {
                placeCooldown--;
            }

            if (!enabled || client.player == null || client.world == null
                    || client.interactionManager == null || client.currentScreen != null) {
                return;
            }

            // Space remains the normal Minecraft jump key; while held, place one block
            // beneath the player after they rise clear of the previous block.
            if (!client.options.jumpKey.isPressed()) {
                lastPlacedY = Integer.MIN_VALUE;
                return;
            }

            var player = client.player;
            if (player.isSpectator() || player.isFallFlying() || placeCooldown > 0
                    || player.getMainHandStack().isEmpty()) {
                return;
            }

            int targetY = (int) Math.floor(player.getY()) - 1;
            if (targetY <= lastPlacedY) {
                return;
            }

            BlockPos target = BlockPos.ofFloored(player.getX(), targetY, player.getZ());
            BlockPos support = target.down();

            if (!client.world.getBlockState(target).isAir()
                    || client.world.getBlockState(support).isAir()) {
                return;
            }

            Vec3d hitPos = new Vec3d(
                    support.getX() + 0.5,
                    support.getY() + 1.0,
                    support.getZ() + 0.5
            );

            BlockHitResult hit = new BlockHitResult(hitPos, Direction.UP, support, false);
            var result = client.interactionManager.interactBlock(player, Hand.MAIN_HAND, hit);
            if (result.isAccepted()) {
                player.swingHand(Hand.MAIN_HAND);
                lastPlacedY = targetY;
                placeCooldown = 3;
            }
        });
    }
}
