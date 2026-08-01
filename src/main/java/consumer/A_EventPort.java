package consumer;

import events.A_Event;

import handler.EventHandler;

import java.util.*;

public abstract class A_EventPort implements I_EventConsumer {

    public A_EventPort() {
        p_SUBSCRIPTIONS = new HashSet<>();
    }

    protected Set<EventHandler> p_SUBSCRIPTIONS;

    public void subscribe(EventHandler handler) {
        p_SUBSCRIPTIONS.add(handler);
        handler.register(this);
    }
    public void subscribe(Collection<EventHandler> handlers) {
        for (EventHandler handler : handlers) subscribe(handler);
    }

    public void free(EventHandler handler) {
        p_SUBSCRIPTIONS.remove(handler);
        handler.remove(this);
    }
    public void free(Collection<EventHandler> handlers) {
        for (EventHandler handler : handlers) free(handler);
    }
    public void free() {
        for (EventHandler handler : p_SUBSCRIPTIONS) handler.remove(this);

        p_SUBSCRIPTIONS.clear();
    }

}