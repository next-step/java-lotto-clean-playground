package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private static List<Integer> autoLottoGenerator() {
        final int RANDOM_VALUE_MAX = 45;
        List<Integer> numList = new ArrayList<>();
        for (int i = 1; i <= RANDOM_VALUE_MAX; i++) {
            numList.add(i);
        }
        Collections.shuffle(numList);
        List<Integer> lottoNumber = numList.subList(0, 6);
        Collections.sort(lottoNumber);
        return lottoNumber;
    }

    public static List<List<Integer>> multipleLottoGenerator(int numberOfAutoLottos) {
        List<List<Integer>> lottoCollection = new ArrayList<>();

        for (int i = 0; i < numberOfAutoLottos; i++) {
            lottoCollection.add(autoLottoGenerator());
        }
        return lottoCollection;
    }
}
