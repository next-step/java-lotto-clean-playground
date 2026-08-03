package domain;

import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class Lotto {
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
        this.randomNumberSet.addAll(userSelectedNumbers);
    }

    private void setLottoNumber(){
        while (randomNumberSet.size() < LOTTO_NUMBER_COUNT) {
            randomNumberSet.add(random.nextInt(1, LOTTO_NUMBER_BOUND + 1));
        }

    }

    public TreeSet<Integer> getRandomNumberSet() {
        return this.randomNumberSet;
    }

}
