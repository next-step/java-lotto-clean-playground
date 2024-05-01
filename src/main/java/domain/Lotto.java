package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Lotto {
    private final int LOTTO_PRICE = 1000;
    private final int LAST_LOTTO_POSITION = 6;

    private int lottoCount;
    private List<Set<Integer>> lottoNumberSet;

    public Lotto(int money) {
        this.lottoCount = (int) money / LOTTO_PRICE;
        lottoNumberSet = new ArrayList<Set<Integer>>(3);
        makeLottoNumberSet(lottoCount);
    }

    public void makeLottoNumberSet(int lottoCount) {
        for(int i = 0;i < lottoCount; i++) {
            lottoNumberSet.add(generateLottoNumber());
        }
    }

    public Set<Integer> generateLottoNumber() {
        Random random = new Random(24);

        Set<Integer> lottoNumber = new HashSet<>();

        while(lottoNumber.size() != LAST_LOTTO_POSITION) {
            lottoNumber.add(random.nextInt(1,46));
        }

        return lottoNumber;
    }

    public int getLottoCount() {
        return this.lottoCount;
    }

    public List<Set<Integer>> getLottoNumberSet() {
        return this.lottoNumberSet;
    }

}
