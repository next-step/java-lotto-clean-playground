package domain;

import java.util.Collections;
import java.util.List;

public class Lotto {

    private List<Integer> lottoNumber;
    private final RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();

    public void generateNumber() {
        lottoNumber = randomNumberGenerator.getRandomNumber();
        Collections.sort(lottoNumber);
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
