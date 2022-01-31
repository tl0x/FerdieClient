package me.tl0x.ferdieclient.base;

public class Command {
    String name;
    String description;
    String[] aliases;

    public Command(String name, String description, String[] aliases){
        this.name = name;
        this.description = description;
        this.aliases = aliases;
    }

    public String getName() {
        return name;
    }
    public String getDesc() {
        return description;
    }
    public String[] getAliases() {
        return aliases;
    }

    public void onExecute(String[] args) {

    }

}
