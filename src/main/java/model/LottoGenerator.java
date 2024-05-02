package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_MAX_NUM = 45;
    private static final int LOTTO_SIZE = 6;

    private final int lottoCount;
    private final List<Integer> lottoNumbers;

    public LottoGenerator(int totalPrice) {
        this.lottoCount = totalPrice / LOTTO_PRICE;
        this.lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= LOTTO_MAX_NUM; i++) {
            this.lottoNumbers.add(i);
        }
    }

    public List<Lotto> generateLotto() {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = new Lotto(generateRandomNumbers());
            lottos.add(lotto);
        }
        return lottos;
    }

    private List<Integer> generateRandomNumbers() {
        Collections.shuffle(this.lottoNumbers);
        List<Integer> values = new ArrayList<>(this.lottoNumbers.subList(0, LOTTO_SIZE));
        Collections.sort(values);
        return values;
    }

}
