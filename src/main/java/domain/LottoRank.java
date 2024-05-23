package domain;

import java.util.List;

public class LottoRank {

    private static final int RESET_NUMBER = 0;
    private static final int INITIAL_NUMBER = 1;

    private final int rankLottoNumber;
    private final Lotto lotto;
    private final List<Integer> lastWeekLottoNumber;

    public LottoRank(Lotto lotto, List<Integer> lastWeekLottoNumber) {
        this.lotto = lotto;
        this.lastWeekLottoNumber = lastWeekLottoNumber;
        this.rankLottoNumber = rankLotto();
    }

    public int getSameLottoNumber() {
        return rankLottoNumber;
    }

    public int rankLotto() {
        int correspondingLottoNumber = RESET_NUMBER;
        for (int elementOfLastWeekLottoNumber : lastWeekLottoNumber) {
            correspondingLottoNumber += checkSameLottoNumber(lotto.getLottoNumber(), elementOfLastWeekLottoNumber);
        }
        return correspondingLottoNumber;
    }

    private int checkSameLottoNumber(List<Integer> lottoNumber, int elementOfLastWeekLottoNumber) {
        if (lottoNumber.contains(elementOfLastWeekLottoNumber)) {
            return INITIAL_NUMBER;
        }
        return RESET_NUMBER;
    }
}
