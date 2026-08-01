package events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class MessageEventTest {

    @Test
    void constructorStoresMessageTest() {
        String message = "hi";
        MessageEvent event = new MessageEvent(message);

        assertSame(message, event.MESSAGE);
    }

}
