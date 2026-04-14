package domain.lotto;

import domain.lotto.exception.WrongLottoNumberException;

public class Ball implements Comparable<Ball> {
    public static final int UPPER_BOUND = 46;
    public static final int LOWER_BOUND = 1;

    private final int number;

    public Ball(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int number) {
        if (number < LOWER_BOUND || number >= UPPER_BOUND) {
            throw new WrongLottoNumberException(
                    "lotto number should be between " + LOWER_BOUND + " and " + (UPPER_BOUND - 1)
            );
        }
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ball)) {
            throw new IllegalArgumentException("object must be an instance of Ball");
        }

        return number == ((Ball) obj).number;
    }

    @Override
    public int compareTo(Ball otherBall) {
        return Integer.compare(this.number, otherBall.number);
    }
}
