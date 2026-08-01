package handler;

import consumer.A_EventConsumer;
import events.A_Event;
import filter.EventFilter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EventHandler extends A_EventConsumer {

    public EventHandler() {
        _filter = null;

        _CONSUMER = new HashSet<>();
    }
    public EventHandler(EventFilter filter) {
        _filter = filter;

        _CONSUMER = new HashSet<>();
    }

    private EventFilter _filter;

    private final Set<A_EventConsumer> _CONSUMER;

    @Override
    protected void p_consume(A_Event event) {
        if (event.isConsumed()) return;
        if (_filter != null && !_filter.filter(event)) return;

        for (A_EventConsumer consumer : _CONSUMER) consumer.consume(event);
    }

    public void register(A_EventConsumer consumer) {
        _CONSUMER.add(consumer);
    }
    public boolean remove(A_EventConsumer consumer) {
        return _CONSUMER.remove(consumer);
    }

    public Optional<EventFilter> getFilter() {
        return Optional.ofNullable(_filter);
    }
    public void setFilter(EventFilter filter) {
        _filter = filter;
    }

}