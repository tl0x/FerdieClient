package me.tl0x.ferdieclient.helpers.events;

import me.tl0x.ferdieclient.base.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EventHandler {

    static final Map<EventType, List<Consumer<Event>>> Handler = new HashMap<>();

    public static void registerEventHandler(EventType eventType, Consumer<Event> handler) {
        if (!Handler.containsKey(eventType)) {
            Handler.put(eventType, new ArrayList<>());
        }
        Handler.get(eventType).add(handler);
    }

    public static boolean fireEvent(EventType event, Event e) {
        if (Handler.containsKey(event)) {
            for (Consumer<Event> handler: Handler.get(event)) {
                handler.accept(e);
            }
        }
        return e.getCancelled();
    }
}
