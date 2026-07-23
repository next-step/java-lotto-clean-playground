package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class lottoGenerator {
    List<Integer> lottoNumbers = new ArrayList<>();
    
    private static List<Integer> generateNumber(List<Integer> lottoNumbers) {
        for (int number = 1; number <= 45; number++ ) {
            lottoNumbers.add(number);
        }

        Collections.shuffle(lottoNumbers);
        Collections.sort(lottoNumbers.subList(0, 6));

        return lottoNumbers;
    }

}


