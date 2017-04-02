package com.github.burningchrome.seqeng;

/**
 *
 */
public class Sequence {



    private static final Sequence _instance = new Sequence();

    // assuming there will only be one listener, not idea but accurate for now
    private GenerateEventListener _listener;

    private long _value = 0;

    private boolean _externalInit = false;

    public static Sequence getInstance() {
        return _instance;
    }

    public long peek() {
        return _value;
    }

    public void init(long currentValue) {

        if (_externalInit) {
            throw new IllegalStateException("sequence has already initialized. can not be called again.");
        }

        _value = currentValue;
        _externalInit = true;

    }

    public long next() {

        long next = -1;

        synchronized (this) {
            _value++;
            next = _value;
        }

        if (next == -1) {
            throw new IllegalStateException("did not get a new value");
        }

        if (_listener != null) {
            try {
                _listener.allocation(new GenerateEvent(next));
            } catch (Exception e) {
                throw new IllegalStateException("error occured writing value to file.");
            }
        }

        return next;

    }

    public void setListener(final GenerateEventListener listener) {

        if (_listener != null) {
            throw new IllegalStateException("there is already a listener assigned");
        }

        _listener = listener;

    }

}
