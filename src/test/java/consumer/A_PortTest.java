package consumer;

import events.A_Event;
import events.TestEvent;
import handler.EventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class A_PortTest {

    EventHandler handler;
    A_Event event;

    @BeforeEach
    void setUp() {
        handler = new EventHandler();
        event = new TestEvent();
    }

    @Test
    void portSubscriptionTest() {
        TestPort port;

        port = new TestPort();

        port.subscribe(handler);
        handler.consume(event);

        assertSame(event, port.poll());
    }
    @Test
    void portSubscribeMultipleTest() {
        TestPort port;
        EventHandler secondHandler;

        port = new TestPort();
        secondHandler = new EventHandler();

        port.subscribe(List.of(handler, secondHandler));
        handler.consume(event);
        secondHandler.consume(event);

        assertTrue(port.poll() != null && port.poll() != null);
    }

    @Test
    void portFreeFromHandlerTest() {
        TestPort port;

        port = new TestPort();

        port.subscribe(handler);
        port.free(handler);
        handler.consume(event);

        assertSame(null, port.poll());
    }
    @Test
    void portFreeMultipleTest() {
        TestPort port;
        EventHandler secondHandler;

        port = new TestPort();
        secondHandler = new EventHandler();

        port.subscribe(List.of(handler, secondHandler));
        port.free(List.of(handler, secondHandler));
        handler.consume(event);
        secondHandler.consume(event);

        assertSame(null, port.poll());
    }

    @Test
    void portFreeGeneralTest() {
        TestPort port;

        port = new TestPort();

        port.subscribe(handler);
        port.free();
        handler.consume(event);

        assertSame(null, port.poll());
    }

}