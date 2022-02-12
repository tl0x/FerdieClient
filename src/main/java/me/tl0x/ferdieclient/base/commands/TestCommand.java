package me.tl0x.ferdieclient.base.commands;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.MinecraftClient;

public class TestCommand extends Command {

    public TestCommand() {
        super("Test","If you see this, I probably forgot to remove it.",new String[]{"alias1"});
    }

    @Override
    public void onExecute(String[] args) {
        helper.sendMessage("Test");
        if (args.length < 3) return;
        else {
            try {
                helper.swapSlots(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            } catch (Exception e) {
                helper.sendMessage("did you do somtehing wrong?");
            }
        }
    }
}
