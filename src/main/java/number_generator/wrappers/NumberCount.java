package number_generator.wrappers;

import exception.NotPositiveCountException;

public class NumberCount {
    private final int value;

    public NumberCount(int value) {
        validatePositive(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validatePositive(int numberCount) {
        if (numberCount <= 0) {
            throw new NotPositiveCountException("Count should be positive");
        }
    }
}
