package events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class A_EventTest {

    @Test
    void consumeMarksEventAsConsumed() {
        TestEvent event = new TestEvent();

        assertFalse(event.isConsumed());
        event.consume();
        assertTrue(event.isConsumed());
    }

    @Test
    void secondConsumeIsSilentByDefault() {
        TestEvent event = new TestEvent();

        event.consume();
        assertDoesNotThrow(event::consume);
    }

    @Test
    void secondConsumeThrowsWhenExceptionModeEnabled() {
        TestEvent strictEvent = new TestEvent(true);

        strictEvent.consume();
        assertThrows(A_Event.AlreadyConsumedException.class, strictEvent::consume);
    }

}