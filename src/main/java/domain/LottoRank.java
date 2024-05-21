package domain;

import java.util.List;

public class LottoRank {

    private static final int RESET_NUMBER = 0;
    private static final int INITIAL_NUMBER = 1;
    private final List<Integer> lastWeekLottoNumber;
    private final int rankLottoNumber;
    private final Lotto lotto;

    public LottoRank(Lotto lotto, List<Integer> lastWeekLottoNumber) {
        this.lotto = lotto;
        this.lastWeekLottoNumber = lastWeekLottoNumber;
        this.rankLottoNumber = rankLotto();
    }

    private int rankLotto() {
        int count = RESET_NUMBER;
        for (int elementOfLastWeekLottoNumber : lastWeekLottoNumber) {
            count += checkSameLottoNumber(lotto.getLottoNumber(), elementOfLastWeekLottoNumber);
        }
        return count;
    }

    private int checkSameLottoNumber(List<Integer> lottoNumber, int elementOfLastWeekLottoNumber) {
        if (lottoNumber.contains(elementOfLastWeekLottoNumber)) {
            return INITIAL_NUMBER;
        }
        return RESET_NUMBER;
    }

    public int getSameLottoNumber() {
        return rankLottoNumber;
    }
}
