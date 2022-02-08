package me.tl0x.ferdieclient.base.commands;

import me.tl0x.ferdieclient.base.Command;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.MinecraftClient;

public class SpamCommand extends Command {

    public SpamCommand() {
        super("spam","Spams a specified message. Usage: .spam msg count", new String[]{});
    }

    @Override
    public void onExecute(String[] args) {
        if(args.length != 3) {
            helper.sendMessage("Use the correct format! .spam msg count (Limit: 200)");
        }
        else {
            try {
                String msg = args[1];
                int count = Integer.parseInt(args[2]);
                if(count > 200) {
                    helper.sendMessage("The limit is 200 messages");
                }
                else {
                    for (int i = 0; i < count; i++) {
                        MinecraftClient.getInstance().player.sendChatMessage(msg);
                    }
                }
            } catch(Exception e) {
                helper.sendMessage("Something went wrong! Did you use the correct format?");
            }


        }
    }
}
