package consumer;

import events.A_Event;

import java.util.function.Consumer;

public class ReactivePort extends A_EventPort {

    public ReactivePort(Consumer<A_Event> reaction) {
        _reaction = reaction;
    }

    private Consumer<A_Event> _reaction;

    public void setReaction(Consumer<A_Event> reaction) {
        _reaction = reaction;
    }

    @Override
    public void consume(A_Event event) {
        _reaction.accept(event);
    }

}