package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumbers {

    private static final int MAX_NUMBER = 45;
    private static final int NUMBERS_PER_TICKET = 6;

    public List<Integer> generateLottoNumber() {
        List<Integer> numbers = new ArrayList<>();
        for (int number = 0; number < MAX_NUMBER; number++) {
            numbers.add(number + 1);
        }

        Collections.shuffle(numbers);
        return numbers.subList(0, NUMBERS_PER_TICKET);
    }
}
