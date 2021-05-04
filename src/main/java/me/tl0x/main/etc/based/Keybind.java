package me.tl0x.main.etc.based;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;

public class Keybind {
    public int keycode;
    boolean pressedbefore = false;

    public Keybind(int kc) {
        this.keycode = kc;
    }

    public boolean isHeld() {
        if (keycode < 0) return false;
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), keycode) && MinecraftClient.getInstance().currentScreen == null;
    }

    public boolean isPressed() {
        if (MinecraftClient.getInstance().currentScreen != null) return false;
        if (keycode < 0) return false;
        boolean flag1 = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), keycode);
        if (flag1 && !pressedbefore) {
            pressedbefore = true;
            return true;
        }
        if (!flag1) pressedbefore = false;
        return false;
    }
}
