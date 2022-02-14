package me.tl0x.ferdieclient.base.modules.misc;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Keybind;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.MouseEvent;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

public class FakeHackerModule extends Module{

    PlayerEntity player = null;

    public FakeHackerModule() {
        super("FakeHacker","r");
        EventHandler.registerEventHandler(EventType.MOUSE_EVENT, event -> {
            if (!this.isEnabled) {
                return;
            }
            if (FerdieClient.client.player == null || FerdieClient.client.world == null) {
                return;
            }
            if (FerdieClient.client.currentScreen != null) {
                return;
            }

            MouseEvent me  = (MouseEvent) event;
            if (me.getAction() == 1 && me.getButton() == 2) {
                HitResult hitResult = FerdieClient.client.crosshairTarget;
                if (hitResult instanceof EntityHitResult eHr && eHr.getEntity() instanceof PlayerEntity) {
                    player = (PlayerEntity) eHr.getEntity();
                }
            }
        });
    }


    @Override
    public void onEnable() {
        player = null;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        player = null;
        super.onDisable();
    }

    @Override
    public void onTick() {
        super.onTick();
        if (player != null) {
            Iterable<Entity> temp = Objects.requireNonNull(FerdieClient.client.world).getEntities();
            List<Entity> entities = new ArrayList<>(StreamSupport.stream(temp.spliterator(),false).toList());
            Collections.shuffle(entities);
            for (Entity e: entities) {
                if (player == e) {
                    continue;
                } else {
                    player.lookAt(EntityAnchorArgumentType.EntityAnchor.FEET, e.getPos());
                    player.swingHand(Hand.MAIN_HAND);
                }
            }
        }
    }

}
