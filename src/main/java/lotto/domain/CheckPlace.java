package lotto.domain;

import java.util.List;
import java.util.Map;

import lotto.Rank;

public class CheckPlace {
    private WinNums winNums;
    private Result result;
    private List<Lotto> lottoList;
    private List<Integer> winList;

    public CheckPlace(LottoGame lottoGame, WinNums winNums) {
        this.winNums = winNums;
        this.lottoList = lottoGame.getLottoList();
        this.winList = winNums.getWinNums();
        result = new Result();
    }

    public Map<Rank, Integer> getResultMap() {
        checkNum();
        return result.getResult();
    }

    private void checkNum() {
        int match;
        boolean isBonus;

        for (Lotto lotto : lottoList) {
            match = 0;
            isBonus = false;
            for (int num : winList) {
                if (lotto.getLottoNums().contains(num)) {
                    match++;
                }
            }
            if (lotto.getLottoNums().contains(winNums.getBonusNum())) {
                isBonus = true;
            }

            if (match >= 3) {
                result.addResult(Rank.of(match, isBonus));
            }
        }
    }

    public Result getResult(){
        return result;
    }
}
