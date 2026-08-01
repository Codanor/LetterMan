package filter;

import events.TestEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventFilterTest {

    EventFilter filter;

    @BeforeEach
    void setUp() {
        filter = new EventFilter(EventFilter.FILTER_TYPE.BLACKLIST);
    }

    @Test
    void addClassAddsToFilterListTest() {
        filter.add(TestEvent.class);

        assertTrue(filter.getEvents().contains(TestEvent.class));
    }

    @Test
    void addEventAddsItsClassToFilterListTest() {
        filter.add(new TestEvent());

        assertTrue(filter.getEvents().contains(TestEvent.class));
    }

    @Test
    void rmvClassRemovesFromFilterListTest() {
        filter.add(TestEvent.class);

        assertTrue(filter.rmv(TestEvent.class));
        assertTrue(filter.getEvents().isEmpty());
    }

    @Test
    void setFilterTypeChangesFilterTypeTest() {
        filter.setFilterType(EventFilter.FILTER_TYPE.WHITELIST);

        assertSame(EventFilter.FILTER_TYPE.WHITELIST, filter.getFilterType());
    }

}
