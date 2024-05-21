package domain;

import java.util.Random;

public class GenerateRandom {
    public int generateRandom() {
        Random random = new Random();

        return random.nextInt(45) + 1;
    }

}
