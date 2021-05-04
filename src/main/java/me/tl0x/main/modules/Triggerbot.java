package me.tl0x.main.modules;


import me.tl0x.main.etc.based.Keybind;
import me.tl0x.main.etc.based.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;

public class Triggerbot extends Module {
    double delay = 300;
    int delayWaited = 1;
    boolean enabled = false;
    private Entity target = null;
    public Triggerbot() {
        super("Triggerbot", "Attacks when entity in crosshair");
    }

    @Override
    public void onEnable() {
        enabled = true;
        super.onEnable();
    }

    @Override
    public void OnTick() {

        if (MinecraftClient.getInstance().player.getHealth() <= 0 || MinecraftClient.getInstance().player.getAttackCooldownProgress(0.5f) < 1)
            return;
        if (!(MinecraftClient.getInstance().targetedEntity instanceof LivingEntity)) return;
        if (((LivingEntity) MinecraftClient.getInstance().targetedEntity).getHealth() <= 0) return;

        if (MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof AxeItem || MinecraftClient.getInstance().player.getMainHandStack().getItem() instanceof SwordItem) {
            target = MinecraftClient.getInstance().targetedEntity;
            attack(target);
        }
        super.OnTick();
    }

    @Override
    public void onDisable() {
        enabled = false;
        super.onDisable();
    }

    public void attack(Entity entity) {
        MinecraftClient.getInstance().interactionManager.attackEntity(MinecraftClient.getInstance().player, entity);
        MinecraftClient.getInstance().player.swingHand(Hand.MAIN_HAND);
    }

    public void fakeHit() {
        MinecraftClient.getInstance().player.swingHand(Hand.MAIN_HAND);
    }


}
