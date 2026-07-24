package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    public static List<Integer> generateNumber() {
        List<Integer> numbers = new ArrayList<>();

        for (int number = 1; number <= 45; number++ ) {
            numbers.add(number);
        }

        Collections.shuffle(numbers);
        List<Integer> lottoNumbers = new ArrayList<>(numbers.subList(0, 6));

        Collections.sort(lottoNumbers);
        return lottoNumbers;
    }

}


