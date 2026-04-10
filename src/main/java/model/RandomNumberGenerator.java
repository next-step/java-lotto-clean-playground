package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomNumberGenerator {
    private static final int MIN_LOTTONUMBER = 1;
    private static final int Max_LOTTONUMBER = 45;

    public List<Integer> getRandomNumberList() {
        List<Integer> pool = new ArrayList<>();

        for (int i = MIN_LOTTONUMBER; i <= Max_LOTTONUMBER; i++) {
            pool.add(i);
        }
        Collections.shuffle(pool);

        return pool.subList(0, 6);
    }

}
