package me.tl0x.ferdieclient.base.modules;

import me.tl0x.ferdieclient.FerdieClient;
import me.tl0x.ferdieclient.base.Keybind;
import me.tl0x.ferdieclient.base.Module;
import me.tl0x.ferdieclient.helpers.Gui.TestGui;
import me.tl0x.ferdieclient.helpers.events.EventHandler;
import me.tl0x.ferdieclient.helpers.events.EventType;
import me.tl0x.ferdieclient.helpers.events.event.KeyboardEvent;
import me.tl0x.ferdieclient.helpers.helper;
import net.minecraft.client.Keyboard;

public class ClickGuiModule extends Module {

    public ClickGuiModule() {
        super("ClickGui", "Gui to activate and edit modules", new Keybind(344));
    }

    @Override
    public void onEnable() {
        FerdieClient.client.setScreen(new TestGui());
        Module.toggle(this);
        super.onEnable();
    }
}
