package me.tl0x.ferdieclient.helpers.events.event;

import me.tl0x.ferdieclient.base.Event;

public class MouseEvent extends Event {
    final int button;
    final int action;

    public MouseEvent(int button, int action) {
        this.button = button;
        this.action = action;
    }

    public int getButton() {
        return button;
    }

    public int getAction() {
        return action;
    }
}
