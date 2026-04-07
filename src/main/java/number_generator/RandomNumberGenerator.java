package number_generator;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    private static final Random random = new Random();
    
    @Override
    public int generate(int min, int max) {
        return random.nextInt(min, max);
    }
}
