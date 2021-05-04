package me.tl0x.main.command;

import me.tl0x.main.etc.CHelper;
import me.tl0x.main.etc.based.Command;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Fake extends Command {
    public Fake() {
        super("Fake", "Fakes a chat message", new String[]{"lol"});
    }

    @Override
    public void onExecute(String[] args) {
        if (args.length < 4) {
            CHelper.sendMessage("Format: IGN Level Message");
            return;
        }
        String IGN = args[1];
        String Level = args[2];
        String Message = "";
        for (int i = 3; i < args.length; i++) {
            Message = Message + args[i];
            Message = Message + " ";
        }
        if (Integer.parseInt(Level) >= 1 && Integer.parseInt(Level) <= 9) {
            MinecraftClient.getInstance().player.sendMessage(Text.of("<[" + Formatting.DARK_GRAY + Level + Formatting.RESET + "] " + IGN + "> " + Message), false);
        }
        if (Integer.parseInt(Level) >= 10 && Integer.parseInt(Level) <= 19) {
            MinecraftClient.getInstance().player.sendMessage(Text.of("<[" + Formatting.GRAY + Level + Formatting.RESET + "] " + IGN + "> " + Message), false);
        }
        if (Integer.parseInt(Level) >= 20 && Integer.parseInt(Level) <= 29) {
            MinecraftClient.getInstance().player.sendMessage(Text.of("<[" + Formatting.YELLOW + Level + Formatting.RESET + "] " + IGN + "> " + Message), false);
        }
        if (Integer.parseInt(Level) >= 30 && Integer.parseInt(Level) <= 39) {
            MinecraftClient.getInstance().player.sendMessage(Text.of("<[" + Formatting.GOLD + Level + Formatting.RESET + "] " + IGN + "> " + Message), false);
        }
        if (Integer.parseInt(Level) >= 40 && Integer.parseInt(Level) <= 49) {
            MinecraftClient.getInstance().player.sendMessage(Text.of("<[" + Formatting.RED + Level + Formatting.RESET + "] " + IGN + "> " + Message), false);
        }
        if (Integer.parseInt(Level) >= 50 && Integer.parseInt(Level) <= 99) {
            MinecraftClient.getInstance().player.sendMessage(Text.of("<[" + Formatting.DARK_RED + Level + Formatting.RESET + "] " + IGN + "> " + Message), false);
        }
        if (Integer.parseInt(Level) >= 100) {
            MinecraftClient.getInstance().player.sendMessage(Text.of("<[" + Formatting.DARK_PURPLE + Level + Formatting.RESET + "] " + IGN + "> " + Message), false);
        }
        super.onExecute(args);
    }
}
