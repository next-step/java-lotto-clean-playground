package domain;

import java.util.List;

public class LottoWinningChecker {
    //로또 당첨 됐는지 확인 로직
    private int firstPrize = 0;
    private int secondPrize = 0;
    private int thirdPrize = 0;
    private int fourthPrize = 0;

    public LottoWinningChecker() {

    }

    public void checkLotto(LottoHistory history, WinningNumbers winningNumbers) {
        for (Lotto lotto : history.getLottos()) {
            int matchCount = countMatch(lotto, winningNumbers);
            updatePrizeCount(matchCount);
        }
    }

    private int countMatch(Lotto lotto, WinningNumbers winningNumbers) {
        List<LottoNumber> lottoNumbers = lotto.getNumbers();
        List<LottoNumber> winningNumbersList = winningNumbers.getWinningNumbers();

        int matchCount = 0;
        for (LottoNumber number : lottoNumbers) {
            if (winningNumbersList.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    private void updatePrizeCount(int matchCount) {
        if(matchCount == 6) {
            firstPrize++;
        } else if(matchCount == 5) {
            secondPrize++;
        } else if(matchCount == 4) {
            thirdPrize++;
        } else if(matchCount == 3) {
            fourthPrize++;
        }
    }

    public int getFirstPrize() {
        return firstPrize;
    }

    public int getSecondPrize() {
        return secondPrize;
    }

    public int getThirdPrize() {
        return thirdPrize;
    }

    public int getFourthPrize() {
        return fourthPrize;
    }
}

