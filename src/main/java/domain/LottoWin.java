package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoWin {

    public List<LottoRank> calculateWinCounts(List<Lotto> lottoList, List<Integer> winNumber, int userBonusNumber, int generateBonusNumber) {
        return addWinCount(lottoList, winNumber, userBonusNumber, generateBonusNumber);
    }

    private List<LottoRank> addWinCount(List<Lotto> lottoList, List<Integer> winNumber, int userBonusNumber, int generateBonusNumber) {
        List<LottoRank> winCount = new ArrayList<>();
        for (Lotto lotto : lottoList) {
            int count = checkWinNumber(lotto.getLottoNumbers(), winNumber);
            boolean bonusMatch = checkBonusMatch(userBonusNumber, generateBonusNumber);
            winCount.add(getLottoRank(count, bonusMatch));
        }
        return winCount;
    }

    public int checkWinNumber(List<Integer> lottoNumbers, List<Integer> winNumber) {
        int count = 0;
        for (int number : winNumber) {
            count += getCount(lottoNumbers, number);
        }
        return count;
    }

    private int getCount(List<Integer> actualNumber, int number) {
        if (actualNumber.contains(number)) {
            return 1;
        }
        return 0;
    }

    public boolean checkBonusMatch(int userBonusNumber, int generatedBonusNumber) {
        return userBonusNumber == generatedBonusNumber;
    }

    private LottoRank getLottoRank(int count, boolean bonusMatch) {
        for (LottoRank rank : LottoRank.values()) {
            if (rank.getCount() == count && (!rank.isBonusMatch() || bonusMatch)) {
                return rank;
            }
        }
        return LottoRank.NONE;
    }
}
