package lotto.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lotto.Constant;

public class RandomNumberGenerator implements NumberGenerator {

    private final Random random = new Random();

    @Override
    public List<Integer> generateLottoNum() {
        List<Integer> lotto = new ArrayList<>();
        int num;

        while(lotto.size() != Constant.LOTTO_NUM_COUNT){
            num = random.nextInt(45) + 1;
            if (!lotto.contains(num)) {
                lotto.add(num);
            }
        }
        Collections.sort(lotto);

        return lotto;
    }
}
