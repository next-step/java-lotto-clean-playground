package domain;

import java.util.Random;

public class RandomLottoNumberGenerator implements NumberGenerator{

    private final static int LOTTO_MAX_NUMBER = 45;

    @Override
    public int generate() {
        Random random = new Random();
        return random.nextInt(LOTTO_MAX_NUMBER) + 1;
    }
}
