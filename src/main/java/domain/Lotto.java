package domain;

import java.util.Random;
import java.util.TreeSet;

public class Lotto {
    public static final int LOTTO_NUMBER_BOUND = 45;
    public static final int LOTTO_NUBER_COUNT = 6;

    TreeSet<Integer> randomNumberSet = new TreeSet<>();
    Random random = new Random();

    public Lotto() { // 로또 한장 생성자
        setLottoNumber();
    }

    private void setLottoNumber(){
        while (randomNumberSet.size() < LOTTO_NUBER_COUNT) {
            randomNumberSet.add(random.nextInt(1, LOTTO_NUMBER_BOUND + 1));
        }

    }

    public TreeSet<Integer> getRandomNumberSet() {
        return this.randomNumberSet;
    }

}
