package events;

public abstract class A_Event {

    public A_Event() {
        _isConsumed = false;

        _IS_CONSUMED_EXCEPTION = false;
    }
    public A_Event(boolean throwsConsumeException) {
        _isConsumed = false;

        _IS_CONSUMED_EXCEPTION = throwsConsumeException;
    }

    private boolean _isConsumed;

    private final boolean _IS_CONSUMED_EXCEPTION;

    public void consume() {
        if (_isConsumed && _IS_CONSUMED_EXCEPTION) throw new AlreadyConsumedException(
                "You cannot consume an already consumed event!"
        );

        _isConsumed = true;
    }
    public boolean isConsumed() {
        return _isConsumed;
    }

    public static class AlreadyConsumedException extends RuntimeException {

        public AlreadyConsumedException() {
            super();
        }
        public AlreadyConsumedException(String message) {
            super(message);
        }

    }

}