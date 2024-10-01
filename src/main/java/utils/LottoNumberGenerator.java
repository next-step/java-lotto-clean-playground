package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    private final List<Integer> lottoNumbers = new ArrayList<>();

    private List<Integer> initializeNumbers() {
        List<Integer> numList = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numList.add(i);
        }
        Collections.shuffle(numList);
        return numList;
    }

    public List<Integer> pickLottoNumbers() {
        lottoNumbers.clear();
        lottoNumbers.addAll(initializeNumbers().subList(0, 6));
        return lottoNumbers;
    }
}
