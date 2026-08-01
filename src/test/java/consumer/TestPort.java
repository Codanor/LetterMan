package consumer;

import events.A_Event;

import java.util.ArrayDeque;
import java.util.Queue;

public class TestPort extends A_EventPort {

    private final Queue<A_Event> _received = new ArrayDeque<>();

    @Override
    protected void p_consume(A_Event event) {
        _received.add(event);
    }

    public A_Event poll() {
        return _received.poll();
    }

}