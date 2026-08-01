package events;

public class TestEvent extends A_Event {

    public TestEvent() {
        super();
    }
    public TestEvent(boolean throwsConsumeException) {
        super(throwsConsumeException);
    }

}
