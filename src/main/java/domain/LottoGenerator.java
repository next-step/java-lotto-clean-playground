package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private final static int MAX_LOTTO_NUMBER = 45;
    private final static int LOTTO_NUMBER_COUNT = 6;

    private static List<Integer> lottoList() {
        List<Integer> lotto = new ArrayList<>();

        for (int i = 0; i < MAX_LOTTO_NUMBER; i++) {
            lotto.add(i + 1);
        }
        return lotto;
    }

    private void lottoShuffle(List<Integer> lotto) {
        Collections.shuffle(lotto);
    }

    private List<LottoNumber> lottoPick(List<Integer> lotto) {
        List<LottoNumber> lottoSix = new ArrayList<>();
        for (int i = 0; i < LOTTO_NUMBER_COUNT; i++) {
            lottoSix.add(LottoNumber.from(lotto.get(i)));
        }

        return lottoSix;
    }

    private void lottoSort(List<LottoNumber> lotto) {
        Collections.sort(lotto);
    }

    public Lotto run() {
        List<Integer> lottoList = lottoList();

        lottoShuffle(lottoList);
        List<LottoNumber> lotto = lottoPick(lottoList);
        lottoSort(lotto);

        return Lotto.from(lotto);
    }

    public List<Lotto> lottoLists(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(run());
        }
        return lottos;
    }


}
