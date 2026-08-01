package consumer;

import events.A_Event;

public abstract class A_EventConsumer {

    public final void consume(A_Event event) {
        if (event.isConsumed()) return;

        p_consume(event);
    }

    protected abstract void p_consume(A_Event event);

}
