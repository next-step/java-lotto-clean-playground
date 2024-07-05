package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator{

    private final Random random = new Random();

    @Override
    public List<Integer> generateLottoNum() {
        List<Integer> lotto = new ArrayList<>();

        for(int i = 0; i < Constant.LOTTO_NUM_COUNT; i++){
            lotto.add(random.nextInt(45));
        }
        Collections.sort(lotto);

        return lotto;
    }
}
