package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    private static List<Integer> initializeNumbers() {
        List<Integer> numList = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numList.add(i);
        }
        Collections.shuffle(numList);

        return numList;
    }

    public static List<Integer> pickLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();

        lottoNumbers.addAll(initializeNumbers().subList(0, 6));
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }
}
