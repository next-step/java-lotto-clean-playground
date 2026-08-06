package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static List<Integer> lottoList() {
        List<Integer> lotto = new ArrayList<>();

        for (int i = 0; i < 45; i++) {
            lotto.add(i+1);
        }
        return lotto;
    }

    private void lottoShuffle(List<Integer> lotto) {
        Collections.shuffle(lotto);
    }

    private List<LottoNumber> lottoPick(List<Integer> lotto) {
        List<LottoNumber> lottoSix = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lottoSix.add(LottoNumber.from(lotto.get(i)));
        }

        return lottoSix;
    }

    private void lottoSort(List<LottoNumber> lotto) {
        Collections.sort(lotto);
    }

    public List<LottoNumber> run() {
        List<Integer> lottoList = lottoList();

        lottoShuffle(lottoList);
        List<LottoNumber> lotto = lottoPick(lottoList);
        lottoSort(lotto);

        return lotto;
    }

    public List<List<LottoNumber>> lottoLists(int count) {
        List<List<LottoNumber>> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(run());
        }
        return lottos;
    }


}
