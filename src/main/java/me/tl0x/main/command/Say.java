package me.tl0x.main.command;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

public class Say extends Command {
    public Say() {
        super("say","says the message you input",new String[]{"say","s"});
    }

    @Override
    public void onExecute(String[] args) {
        if (args.length < 2) {
            CHelper.sendMessage("You didn't write out a message.");
        }
        else {
            String message = "";
            for(int i = 1; i < args.length; i++) {
                message = message + args[i];
            }
            CHelper.sendMessage("WIP " + message);
        }
        super.onExecute(args);
    }
}
