package me.tl0x.main.command;

import me.tl0x.main.etc.based.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Clipboard;
import net.minecraft.util.math.Vec3d;

public class CopyCoordinates extends Command{
    public CopyCoordinates() {
        super("CopyCoordinates","Copies your coordinates to clipboard",new String[]{"ccoord"});
    }

    String coord = "";

    @Override
    public void onExecute(String[] args) {
        Vec3d pos = MinecraftClient.getInstance().player.getPos();
        coord = Integer.toString((int)pos.x) + " " + Integer.toString((int)pos.y) + " " + Integer.toString((int)pos.z);
        new Clipboard().setClipboard(MinecraftClient.getInstance().getWindow().getHandle(),coord);
        super.onExecute(args);
    }

}
