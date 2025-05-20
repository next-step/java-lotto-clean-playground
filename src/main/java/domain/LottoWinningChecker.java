package domain;

import java.util.List;

public class LottoWinningChecker {
    //로또 당첨 됐는지 확인 로직
    private int firstPrizeWinCount = 0;
    private int secondPrizeWinCount = 0;
    private int thirdPrizeWinCount = 0;
    private int fourthPrizeWinCount = 0;

    public LottoWinningChecker() {

    }

    public void checkLotto(Lottos history, WinningNumbers winningNumbers) {
        for (Lotto lotto : history.getLottos()) {
            int matchCount = countMatch(lotto, winningNumbers);
            updatePrizeCount(matchCount);
        }
    }

    private int countMatch(Lotto lotto, WinningNumbers winningNumbers) {
        List<LottoNumber> lottoNumbers = lotto.getNumbers();
        List<LottoNumber> winningNumbersList = winningNumbers.getWinningNumbers().getNumbers();

        return (int) lottoNumbers.stream()
                .filter(winningNumbersList::contains)
                .count();
    }

    private void updatePrizeCount(int matchCount) {
        if(matchCount == 6) {
            firstPrizeWinCount++;
        } else if(matchCount == 5) {
            secondPrizeWinCount++;
        } else if(matchCount == 4) {
            thirdPrizeWinCount++;
        } else if(matchCount == 3) {
            fourthPrizeWinCount++;
        }
    }

    public int getFirstPrizeWinCount() {
        return firstPrizeWinCount;
    }

    public int getSecondPrizeWinCount() {
        return secondPrizeWinCount;
    }

    public int getThirdPrizeWinCount() {
        return thirdPrizeWinCount;
    }

    public int getFourthPrizeWinCount() {
        return fourthPrizeWinCount;
    }
}

