package me.tl0x.main.modules;


import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.hit.EntityHitResult;

public class Triggerbot extends Module {
    double delay = 300;
    int delayWaited = 1;
    boolean enabled = false;
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

        if (MinecraftClient.getInstance().crosshairTarget instanceof EntityHitResult && enabled == true) {
            delayWaited++;
            if (delayWaited > delay) {
                delayWaited = 0;
            } else return;
            EntityHitResult ehr = (EntityHitResult) MinecraftClient.getInstance().crosshairTarget;
            if (!ehr.getEntity().isAttackable() || !ehr.getEntity().isAlive()) return;
            assert MinecraftClient.getInstance().interactionManager != null;
             MinecraftClient.getInstance().interactionManager.attackEntity(MinecraftClient.getInstance().player,ehr.getEntity());
        }
        super.OnTick();
    }

    @Override
    public void onDisable() {
        enabled = false;
        super.onDisable();
    }


}
