package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator {

    private final ArrayList<Integer> randomNumber;

    public RandomNumberGenerator() {
        randomNumber = IntStream.rangeClosed(1, 45)
            .boxed()
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Integer> getRandomNumber() {
        Collections.shuffle(randomNumber);
        return randomNumber.subList(0, 6);
    }
}
