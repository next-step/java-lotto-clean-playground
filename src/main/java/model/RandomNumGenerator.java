package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumGenerator {

    public static List<Integer> randomNumGenerate() {
        List<Integer> numList = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            numList.add(i);
        }

        Collections.shuffle(numList);

        return numList.subList(0, 6);
    }
}
