package consumer;

import events.A_Event;

public interface I_EventConsumer {

    void consume(A_Event event);

}