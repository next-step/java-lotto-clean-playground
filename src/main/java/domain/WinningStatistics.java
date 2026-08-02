package domain;

import java.util.List;

public class WinningStatistics {
    private int fourthPlace = 0; // 3개 일치
    private int thirdPlace = 0; // 4개 일치
    private int secondPlace = 0; // 5개 일치
    private int firstPlace = 0; // 6개 일치
    private int temptCount = 0;
    MatchCount matchCount = new MatchCount();

    public void compareLottos (List<Integer> winningNumbers, Lottos lottos) {
        for (LottoNumber lottoNumber : lottos.getLottos()) {
            matchCount.comparingLotto(lottoNumber.getLottoNumbers(), winningNumbers);
            temptCount = matchCount.getCount();
            setPlace(temptCount);
        }
    }

    private void setPlace(int temptCount) {
        if (temptCount == 3) {
            fourthPlace++;
        }
        if (temptCount == 4) {
            thirdPlace++;
        }
        if (temptCount == 5) {
            secondPlace++;
        }
        if (temptCount == 6) {
            firstPlace++;
        }
    }

    public int getFourthPlace() {
        return fourthPlace;
    }
    public int getThirdPlace() {
        return thirdPlace;
    }
    public int getSecondPlace() {
        return secondPlace;
    }
    public int getFirstPlace() {
        return firstPlace;
    }
}
