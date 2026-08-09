package domain;

import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class Lotto {
    public static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    public static final int LOTTO_NUMBER_BOUND = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;

    TreeSet<Integer> numbers = new TreeSet<>();
    Random random = new Random();

    public Lotto() {
        generateRandomNumbers();
    }

    public Lotto(List<Integer> userSelectedNumbers) {
        if (userSelectedNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는" + LOTTO_NUMBER_COUNT + "개여야 합니다.");
        }
        for (Integer number : userSelectedNumbers) {
            if (number < LOTTO_NUMBER_LOWER_BOUND || number > LOTTO_NUMBER_BOUND) {
                throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBER_LOWER_BOUND
                        + "부터 " + LOTTO_NUMBER_BOUND + " 사이의 숫자여야 합니다.");
            }
        }
        this.numbers.addAll(userSelectedNumbers);
        if (this.numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public TreeSet<Integer> getNumbers() {
        return this.numbers;
    }

    private void generateRandomNumbers() {
        while (numbers.size() < LOTTO_NUMBER_COUNT) {
            numbers.add(random.nextInt(LOTTO_NUMBER_LOWER_BOUND, LOTTO_NUMBER_BOUND + 1));
        }

    }

}
