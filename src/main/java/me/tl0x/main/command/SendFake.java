package me.tl0x.main.command;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class SendFake extends Command {
    public SendFake() {
        super("SendFake", "Send a fake DM", new String[]{"ll"});
    }

    @Override
    public void onExecute(String[] args) {
        if (args.length < 3) {
            CHelper.sendMessage("Format: IGN Message");
            return;
        }
        String IGN = args[1];
        String Message = "";

        for (int i = 2; i < args.length; i++) {
            Message = Message + args[i];
            Message = Message + " ";
        }

        MinecraftClient.getInstance().player.sendMessage(Text.of(Formatting.LIGHT_PURPLE + "You whisper to " + IGN + ":" + " " + Message), false);


    }
}
