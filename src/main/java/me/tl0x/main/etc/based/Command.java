package me.tl0x.main.etc.based;


public class Command {
    String displayName;
    String description;
    String[] aliases;

    public Command(String name, String description, String[] aliases) {
        this.aliases = aliases;
        this.displayName = name;
        this.description = description;
    }

    public final String[] getAliases() {
        return aliases;
    }

    public final String getDisplayName() {
        return displayName;
    }

    public final String getDescription() {
        return description;
    }

    public void onExecute(String[] args) {
    }
}
