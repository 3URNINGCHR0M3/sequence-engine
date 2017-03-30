package com.github.burningchrome.seqeng;

/**
 *
 */
public class Sequence {

    private static final Sequence _instance = new Sequence();

    private long _value = 0;

    public long peek() {
        return _value;
    }

    public static Sequence getInstance() {
        return _instance;
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

        return next;

    }




}
