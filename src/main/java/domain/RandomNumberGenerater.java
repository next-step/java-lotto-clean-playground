package domain;

import java.util.Random;

public class RandomNumberGenerater implements NumberGenerater{

    private static final int MAX_NUMBER = 45;

    @Override
    public int generate() {
        final Random random = new Random();
        return random.nextInt(MAX_NUMBER)+1;
    }
}
