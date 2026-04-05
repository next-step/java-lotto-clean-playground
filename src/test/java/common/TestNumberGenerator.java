package common;

import java.util.List;

public class TestNumberGenerator implements NumberGenerator {
    private static String GENERATING_NUMBERS_TOO_MANY_TIMES = "너무 많은 숫자 반환 중";
    private List<Integer> numbers;
    private int index = 0;

    public TestNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public int generateNumber() {
        try {
            return numbers.get(index++);
        } catch (Exception e) {
            throw new IllegalStateException(GENERATING_NUMBERS_TOO_MANY_TIMES);
        }
    }
}
