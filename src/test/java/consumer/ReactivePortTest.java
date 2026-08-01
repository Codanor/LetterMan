package consumer;

import events.A_Event;
import events.TestEvent;
import handler.EventHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ReactivePortTest {

    EventHandler handler;
    A_Event event;

    @BeforeEach
    void setUp() {
        handler = new EventHandler();
        event = new TestEvent();
    }

    @Test
    void reactionFiresOnConsumeTest() {
        ReactivePort port;
        A_Event[] received;

        received = new A_Event[1];
        port = new ReactivePort(e -> received[0] = e);

        port.subscribe(handler);
        handler.consume(event);

        assertSame(event, received[0]);
    }

    @Test
    void setReactionReplacesReactionTest() {
        ReactivePort port;
        A_Event[] received;

        received = new A_Event[1];
        port = new ReactivePort(e -> {});
        port.setReaction(e -> received[0] = e);

        port.subscribe(handler);
        handler.consume(event);

        assertSame(event, received[0]);
    }

}
