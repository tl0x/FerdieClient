package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.MouseEvent;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

import java.util.List;
import java.util.Objects;

public class GuiModule extends Module {

    PlayerEntity player = null;

    public static boolean enabled = false;
    public GuiModule() {
        super("Gui","Enables or Disables the GUI.");

        EventHandler.registerEventHandler(EventType.MOUSE_EVENT, event -> {
            if (!this.isEnabled) {
                return;
            }
            if (MinecraftClient.getInstance().player == null || MinecraftClient.getInstance().world == null) {
                return;
            }
            if (MinecraftClient.getInstance().currentScreen != null) {
                return;
            }

            MouseEvent me  = (MouseEvent) event;
            if (me.getAction() == 1 && me.getButton() == 2) {
                HitResult hitResult = MinecraftClient.getInstance().crosshairTarget;
                if (hitResult instanceof EntityHitResult eHr && eHr.getEntity() instanceof PlayerEntity) {
                    player = (PlayerEntity) eHr.getEntity();
                }
            }
        });
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onTick() {
        if (player != null) {
            helper.sendMessage("Hello");
        }
        super.onTick();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
