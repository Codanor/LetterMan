package events;

public class MessageEvent extends A_Event {

    public MessageEvent(String message) {
        MESSAGE = message;
    }

    public final String MESSAGE;

}