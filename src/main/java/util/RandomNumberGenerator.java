package util;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomNumberGenerator implements NumberGenerator {
    private final Random random;

    public RandomNumberGenerator() {
        this.random = new Random();
    }

    @Override
    public List<Integer> generate() {
        List<Integer> pool = new java.util.ArrayList<>(IntStream.rangeClosed(1, 45)
                .boxed()
                .toList());
        Collections.shuffle(pool, random);
        return pool.subList(0, 6);
    }
}
