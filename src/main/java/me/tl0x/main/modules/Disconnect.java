package me.tl0x.main.modules;

import me.tl0x.main.etc.based.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class Disconnect extends Module {
    // Add a keybind system already, then this module might actually be useful

    // no lol

    public Disconnect() {
        super("Disconnect","Automatically disconnects from server. aka: ez log");
    }

    @Override
    public void onEnable() {
        MinecraftClient.getInstance().getNetworkHandler().getConnection().disconnect(Text.of("You activated the Disconnect module"));
        this.isEnabled = false;
        super.onEnable();
    }
}
