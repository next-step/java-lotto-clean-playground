package domain;

import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class Lotto {
    public static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    public static final int LOTTO_NUMBER_BOUND = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;

    TreeSet<Integer> randomNumberSet = new TreeSet<>();
    Random random = new Random();

    public Lotto() {
        setLottoNumber();
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
        this.randomNumberSet.addAll(userSelectedNumbers);
        if (this.randomNumberSet.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public TreeSet<Integer> getRandomNumberSet() {
        return this.randomNumberSet;
    }

    private void setLottoNumber() {
        while (randomNumberSet.size() < LOTTO_NUMBER_COUNT) {
            randomNumberSet.add(random.nextInt(LOTTO_NUMBER_LOWER_BOUND, LOTTO_NUMBER_BOUND + 1));
        }

    }

}
