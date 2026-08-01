package consumer;

import events.A_Event;
import events.TestEvent;
import handler.EventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueuePortTest {

    EventHandler handler;
    A_Event event;

    @BeforeEach
    void setUp() {
        handler = new EventHandler();
        event = new TestEvent();
    }

    @Test
    void peekReturnsWithoutRemovingTest() {
        QueuePort port;

        port = new QueuePort();

        port.subscribe(handler);
        handler.consume(event);

        assertSame(event, port.peek());
        assertSame(event, port.peek());
    }

    @Test
    void pollRemovesEventTest() {
        QueuePort port;

        port = new QueuePort();

        port.subscribe(handler);
        handler.consume(event);

        assertSame(event, port.poll());
        assertSame(null, port.poll());
    }

    @Test
    void clearEmptiesQueueTest() {
        QueuePort port;

        port = new QueuePort();

        port.subscribe(handler);
        handler.consume(event);
        port.clear();

        assertSame(null, port.peek());
    }

    @Test
    void containsFindsQueuedEventTest() {
        QueuePort port;

        port = new QueuePort();

        port.subscribe(handler);
        handler.consume(event);

        assertTrue(port.contains(event));
    }

}
