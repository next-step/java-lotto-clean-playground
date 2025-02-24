package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomNumberGenerator {

    private final List<Integer> numbers;

    public RandomNumberGenerator() {
        numbers = IntStream.rangeClosed(Constant.MIN_NUM, Constant.MAX_NUM)
            .boxed()
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Integer> getNumbers() {
        List<Integer> randomNumber = new ArrayList<>(numbers);
        Collections.shuffle(randomNumber);
        return randomNumber.subList(0, 6);
    }
}
