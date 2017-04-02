package com.github.burningchrome.seqeng;

/**
 *
 */
public class GenerateEvent implements Comparable<GenerateEvent> {

    private final long _value;

    public GenerateEvent(final long value) {
        _value = value;
    }

    public long getValue() {
        return _value;
    }

    public int compareTo(final GenerateEvent e) {
        throw new UnsupportedOperationException();
//        return 0;
    }

}
