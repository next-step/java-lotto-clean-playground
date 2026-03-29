package model;

import constants.LottoSettingsConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class LottoBatch {
    List<Lotto> lottos;

    public LottoBatch(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public void add(Lotto lotto) {
        this.lottos.add(lotto);
    }

    public List<Lotto> getAllLotto() {
        return List.copyOf(this.lottos);
    }

    public List<Integer> getMatchCountPerLotto(List<Integer> winningNumbers) {
        List<Integer> result = new ArrayList<>();

        for (Lotto lotto : this.lottos) {
            result.add(this.countMatches(lotto.getNumbers(), winningNumbers));
        }

        return result;
    }

    private int countMatches(List<Integer> lottoNumber, List<Integer> winningNumber) {
        Set<Integer> lottoNumberSet = new HashSet<>(lottoNumber);
        Set<Integer>  winningNumberSet = new HashSet<>(winningNumber);
        lottoNumberSet.retainAll(winningNumberSet);
        // check if winning nubmer is valid

        return lottoNumberSet.size();
    }
}
