package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoNumberGenerator
        implements LottoNumberGenerator {

    private static final int LOTTO_SIZE = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> numbers = createNumbers();
        Collections.shuffle(numbers);

        return new ArrayList<>(
                numbers.subList(0, LOTTO_SIZE)
        );
    }

    private List<Integer> createNumbers() {
        List<Integer> numbers = new ArrayList<>();

        for (int number = LottoNumber.MIN_NUMBER;
             number <= LottoNumber.MAX_NUMBER;
             number++) {
            numbers.add(number);
        }

        return numbers;
    }
}
