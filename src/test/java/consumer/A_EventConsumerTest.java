package consumer;

import events.A_Event;
import events.TestEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class A_EventConsumerTest {

    A_Event event;

    @BeforeEach
    void setUp() {
        event = new TestEvent();
    }

    @Test
    void consumeDelegatesToPConsumeTest() {
        TestPort consumer;

        consumer = new TestPort();

        consumer.consume(event);

        assertSame(event, consumer.poll());
    }

    @Test
    void consumeSkipsAlreadyConsumedEventTest() {
        TestPort consumer;

        consumer = new TestPort();
        event.consume();

        consumer.consume(event);

        assertSame(null, consumer.poll());
    }

}
