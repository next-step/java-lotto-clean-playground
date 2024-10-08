package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {

    public final static int MIN_NUMBER = 1;
    public final static int MAX_NUMBER = 45;
    public final static int LOTTO_SIZE = 6;


    private static List<Integer> initializeNumbers() {
        List<Integer> numList = new ArrayList<>();

        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            numList.add(i);
        }
        Collections.shuffle(numList);

        return numList;
    }

    public static List<Integer> pickLottoNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();

        lottoNumbers.addAll(initializeNumbers().subList(0, LOTTO_SIZE));
        Collections.sort(lottoNumbers);

        return lottoNumbers;
    }
}
