package model;

import constants.LottoSettingsConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoBatch {
    private final List<Integer> WINNING_COUNT = List.of(3,4,5,6);
    private final List<Integer> WINNING_PRICE = List.of(
            LottoSettingsConstants.THREE_MATCH_PRICE,
            LottoSettingsConstants.FOUR_MATCH_PRICE,
            LottoSettingsConstants.FIVE_MATCH_PRICE,
            LottoSettingsConstants.SIX_MATCH_PRICE
    );
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

    public double getReturnRatio(List<Integer> winningNumbers) {
        List<Integer> result = this.getMatchCountPerLotto(winningNumbers);

        double earnResult = 0.0;
        for (int i = 0; i < WINNING_COUNT.size(); i++) {
            int currentCount = WINNING_COUNT.get(i);
            int totalCount = result.stream().filter(matchCount -> currentCount == matchCount).toList().size();

            earnResult += totalCount * WINNING_PRICE.get(i);
        }

        return earnResult / (LottoSettingsConstants.LOTTO_PRICE * this.lottos.size());
    }

    private int countMatches(List<Integer> lottoNumber, List<Integer> winningNumber) {
        Set<Integer> lottoNumberSet = new HashSet<>(lottoNumber);
        Set<Integer>  winningNumberSet = new HashSet<>(winningNumber);
        lottoNumberSet.retainAll(winningNumberSet);
        // check if winning nubmer is valid

        return lottoNumberSet.size();
    }
}
