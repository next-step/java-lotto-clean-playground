package controller;

import model.Lotto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class LottosGenerator {

    private Random random = new Random();

    public List<Lotto> genLottos(int autoAmount) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < autoAmount; i++) {
            lottos.add(genLotto());
        }

        return lottos;
    }

    public Lotto genLotto() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            int randomNum = random.nextInt(45) + 1;

            while (nums.contains(randomNum)) {
                randomNum = random.nextInt(45) + 1;
            }
            nums.add(randomNum);
        }
        nums.sort(Comparator.naturalOrder());
        return new Lotto(nums);
    }
}
