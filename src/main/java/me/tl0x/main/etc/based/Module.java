package me.tl0x.main.etc.based;

public class Module {
    String name;
    String desc;
    public boolean isEnabled = false;
    public Module(String n, String d) {
        this.name = n;
        this.desc = d;
    }

    public void OnTick() { }
    public void onEnable() { }
    public void onDisable() { }

    public final String getName() {return name;}
    public final String getDesc() {return desc;}
}
