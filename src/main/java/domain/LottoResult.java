package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoResult {

    private boolean containsWinningNumber(Lotto lottoList, LottoNumber win) {
        return lottoList.contains(win);
    }

    private int resultCounting(Lotto lottoList, LottoNumber win, int count) {
        if (containsWinningNumber(lottoList, win)) {
            count++;
        }
        return count;
    }

    private Rank checkingWinningNumbers(Lotto lottoList, Lotto wins, LottoNumber bonusBall) {
        int count = lottoList.countLotto(wins);

        boolean matchBonus = containsWinningNumber(lottoList, bonusBall);
        return Rank.find(count, matchBonus);
    }

    public List<Rank> calculateRanks(List<Lotto> lottos, WinningLotto winningLotto) {
        List<Rank> ranks = new ArrayList<>();

        for (Lotto lotto : lottos) {
            ranks.add(checkingWinningNumbers(lotto, winningLotto.getWinningLotto(), winningLotto.getBonusBall()));
        }
        return ranks;
    }
}
