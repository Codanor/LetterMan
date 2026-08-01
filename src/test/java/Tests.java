import events.TestEvent;
import filter.EventFilter;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

    @Test
    void whitelistPassesOnlyListedEvents() {
        EventFilter filter = new EventFilter(EventFilter.FILTER_TYPE.WHITELIST, Set.of(TestEvent.class));

        assertTrue(filter.filter(new TestEvent()));
    }

    @Test
    void blacklistBlocksListedEvents() {
        EventFilter filter = new EventFilter(EventFilter.FILTER_TYPE.BLACKLIST, Set.of(TestEvent.class));

        assertFalse(filter.filter(new TestEvent()));
    }

}