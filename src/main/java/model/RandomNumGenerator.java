package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumGenerator {
    private final static int LOTTO_NUMBER_MIN = 1;
    private final static int LOTTO_NUMBER_MAX = 45;

    public static List<Integer> randomNumGenerate() {
        List<Integer> numList = new ArrayList<>();
        for (int i = LOTTO_NUMBER_MIN; i <= LOTTO_NUMBER_MAX; i++) {
            numList.add(i);
        }
        Collections.shuffle(numList);
        return numList.subList(0, 6);
    }
}
