package handler;

import consumer.I_EventConsumer;
import events.A_Event;
import filter.EventFilter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class EventHandler implements I_EventConsumer {

    public EventHandler() {
        _filter = null;

        _CONSUMER = new HashSet<>();
    }
    public EventHandler(EventFilter filter) {
        _filter = filter;

        _CONSUMER = new HashSet<>();
    }

    private EventFilter _filter;

    private final Set<I_EventConsumer> _CONSUMER;

    @Override
    public void consume(A_Event event) {
        if (_filter != null && !_filter.filter(event)) return;

        for (I_EventConsumer consumer : _CONSUMER) consumer.consume(event);
    }

    public void register(I_EventConsumer consumer) {
        _CONSUMER.add(consumer);
    }
    public boolean remove(I_EventConsumer consumer) {
        return _CONSUMER.remove(consumer);
    }

    public Optional<EventFilter> getFilter() {
        return Optional.ofNullable(_filter);
    }
    public void setFilter(EventFilter filter) {
        _filter = filter;
    }

}