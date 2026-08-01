package handler;

import consumer.TestPort;
import events.A_Event;
import events.TestEvent;
import filter.EventFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventHandlerTest {

    EventHandler handler;
    A_Event event;

    @BeforeEach
    void setUp() {
        handler = new EventHandler();
        event = new TestEvent();
    }

    @Test
    void registerReceivesConsumedEventsTest() {
        TestPort port;

        port = new TestPort();

        handler.register(port);
        handler.consume(event);

        assertSame(event, port.poll());
    }

    @Test
    void removeStopsReceivingEventsTest() {
        TestPort port;

        port = new TestPort();

        handler.register(port);
        assertTrue(handler.remove(port));
        handler.consume(event);

        assertSame(null, port.poll());
    }

    @Test
    void constructorWithFilterSetsFilterTest() {
        EventFilter filter;
        EventHandler filteredHandler;

        filter = new EventFilter(EventFilter.FILTER_TYPE.BLACKLIST);
        filteredHandler = new EventHandler(filter);

        assertSame(filter, filteredHandler.getFilter().get());
    }

    @Test
    void setFilterBlocksNonWhitelistedEventsTest() {
        TestPort port;
        EventFilter filter;

        port = new TestPort();
        filter = new EventFilter(EventFilter.FILTER_TYPE.WHITELIST);

        handler.register(port);
        handler.setFilter(filter);
        handler.consume(event);

        assertSame(null, port.poll());
    }

    @Test
    void alreadyConsumedEventIsNotDistributedTest() {
        TestPort port;

        port = new TestPort();
        event.consume();

        handler.register(port);
        handler.consume(event);

        assertSame(null, port.poll());
    }

}
