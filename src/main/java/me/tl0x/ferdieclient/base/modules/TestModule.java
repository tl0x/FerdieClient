package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.Gui.TestGui;
import net.minecraft.client.MinecraftClient;

public class TestModule extends Module {
    int i = 0;
    public TestModule() {
        super("Test","If you see this, I probably forgot to remove it.");
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onTick() {

        i++;
        if (i>20) {
            i = 0;
            MinecraftClient.getInstance().setScreen(new TestGui());
            this.toggle();
        }
        super.onTick();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
