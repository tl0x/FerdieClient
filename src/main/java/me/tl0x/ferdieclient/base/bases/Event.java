package me.tl0x.ferdieclient.base.bases;

public class Event {

    boolean cancelEvent = false;

    public boolean getCancelled() {
        return cancelEvent;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelEvent = cancelled;
    }
}
