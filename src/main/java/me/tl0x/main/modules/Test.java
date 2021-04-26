package me.tl0x.main.modules;


import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Module;
import net.minecraft.client.MinecraftClient;

public class Test extends Module {
    public Test() {
        super("Test", "Allows you to go zoom");
    }

    @Override
    public void onEnable() {
        CHelper.sendMessage("Toggled Speed on!");
        MinecraftClient.getInstance().player.abilities.setWalkSpeed(0.2f);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        CHelper.sendMessage("Toggled Speed off!");
        MinecraftClient.getInstance().player.abilities.setWalkSpeed(1f);
        super.onDisable();
    }


}
