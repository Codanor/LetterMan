package consumer;

import events.A_Event;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueuePort extends A_EventPort {

    public QueuePort() {
        _queue = new ArrayDeque<>();
    }

    private Queue<A_Event> _queue;

    @Override
    protected void p_consume(A_Event event) {
        _queue.add(event);
    }

    public A_Event peek() {
        return _queue.peek();
    }
    public A_Event poll() {
        return _queue.poll();
    }

    public void clear() {
        _queue.clear();
    }

    public boolean contains(A_Event event) {
        return _queue.contains(event);
    }

}