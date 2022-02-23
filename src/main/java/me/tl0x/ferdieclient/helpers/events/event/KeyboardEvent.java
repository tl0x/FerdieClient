package me.tl0x.ferdieclient.helpers.events.event;

import me.tl0x.ferdieclient.base.bases.Event;

public class KeyboardEvent extends Event {
    int keyCode;
    int action;

    public KeyboardEvent(int keyCode, int action) {
        this.keyCode = keyCode;
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public int getKeyCode() {
        return keyCode;
    }
}
