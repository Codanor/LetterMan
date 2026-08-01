package filter;

import events.A_Event;

import java.util.HashSet;
import java.util.Set;

public class EventFilter {

    public EventFilter(FILTER_TYPE type) {
        _filterType = type;

        _FILTER_LIST = new HashSet<>();
    }
    public EventFilter(FILTER_TYPE type, Set<Class<? extends A_Event>> events) {
        _filterType = type;

        _FILTER_LIST = new HashSet<>(events);
    }

    public enum FILTER_TYPE {
        WHITELIST,
        BLACKLIST,
    }

    private FILTER_TYPE _filterType;

    private final Set<Class<? extends A_Event>> _FILTER_LIST;

    public void add(Class<? extends A_Event> eventClass) {
        _FILTER_LIST.add(eventClass);
    }
    public void add(A_Event event) {
        add(event.getClass());
    }

    public boolean rmv(Class<? extends A_Event> eventClass) {
        return _FILTER_LIST.remove(eventClass);
    }
    public boolean rmv(A_Event event) {
        return rmv(event.getClass());
    }

    public Set<Class<? extends A_Event>> getEvents() {
        return new HashSet<>(_FILTER_LIST);
    }

    public void setFilterType(FILTER_TYPE type) {
        _filterType = type;
    }
    public FILTER_TYPE getFilterType() {
        return _filterType;
    }

    public boolean filter(A_Event event) {
        switch (_filterType) {
            case BLACKLIST -> {return !_FILTER_LIST.contains(event.getClass());}
            case WHITELIST -> {return _FILTER_LIST.contains(event.getClass());}
        }

        return false;
    }

}