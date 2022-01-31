package me.tl0x.ferdieclient.commands;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class TestCommand extends Command {

    public TestCommand() {
        super("Test","If you see this, I probably forgot to remove it.",new String[]{"alias1"});
    }

    @Override
    public void onExecute(String[] args) {
        helper.sendMessage("Test");
        MinecraftClient.getInstance().player.sendChatMessage("Amongus");
    }
}
