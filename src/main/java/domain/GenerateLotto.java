package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GenerateLotto {
    ArrayList<ArrayList<Integer>> totalLotto = new ArrayList<>();

    private ArrayList<Integer> getOne() {
        GenerateRandom random = new GenerateRandom();

        Set<Integer> set = new HashSet<>();

        while (set.size() != 6) {
            set.add(random.generateRandom());
        }
        ArrayList<Integer> lotto = new ArrayList<>(set);


        Collections.sort(lotto);

        return lotto;
    }

    public ArrayList<ArrayList<Integer>> getLotto(int money) {
        for (int i = 0; i < money; i += 1000) {
            totalLotto.add(this.getOne());
        }

        Collections.shuffle(totalLotto);
        return totalLotto;
    }
}
