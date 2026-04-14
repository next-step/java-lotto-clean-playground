package number_generator.wrappers;

public class Count {
    private final int value;

    public Count(int value) {
        validate(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validate(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("count must be greater than zero");
        }
    }
}
