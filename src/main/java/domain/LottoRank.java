package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoRank {

    private static final int RESET_NUMBER = 0;
    private static final int INITIAL_NUMBER = 1;
    private static final int FIRST_LOTTO_RANK_NUMBER = 3;
    private static final int LAST_LOTTO_RANK_NUMBER = 6;
    private final List<Integer> countSameLottoNumber = new ArrayList<>();
    private final List<Integer> lottoRank = new ArrayList<>();
    private final Lottos lottos;

    public LottoRank(Lottos lottos) {
        this.lottos = lottos;
    }

    public void countCorrectLottoNumber(List<Integer> lastWeekLottoNumber) {
        List<Lotto> lottoList = lottos.getLottos();
        for (Lotto lotto : lottoList) {
            int correctLottoNumber = checkLottoNumber(lotto.getLottoNumber(), lastWeekLottoNumber);
            countSameLottoNumber.add(correctLottoNumber);
        }
    }

    private int checkLottoNumber(List<Integer> lottoNumber, List<Integer> lastWeekLottoNumber) {
        int correctLottoNumber = RESET_NUMBER;
        for (int elementOfLottoNumber : lottoNumber) {
            int checkSameNumber = checkSingleLottoNumber(lastWeekLottoNumber, elementOfLottoNumber);
            correctLottoNumber += checkSameNumber;
        }
        return correctLottoNumber;
    }

    private int checkSingleLottoNumber(List<Integer> lastWeekLottoNumber, int elementOfLottoNumber) {
        int sameLottoNumber = RESET_NUMBER;
        for (int elementOfLastWeekLottoNumber : lastWeekLottoNumber) {
            int checkSameLottoNumber = checkSameNumber(elementOfLottoNumber, elementOfLastWeekLottoNumber);
            sameLottoNumber += checkSameLottoNumber;
        }
        return sameLottoNumber;
    }

    private int checkSameNumber(int elementOfLottoNumber, int elementOfLastWeekLottoNumber) {
        if (elementOfLottoNumber == elementOfLastWeekLottoNumber) {
            return INITIAL_NUMBER;
        }
        return RESET_NUMBER;
    }

    public void rankLotto() {
        for (int i = FIRST_LOTTO_RANK_NUMBER; i < LAST_LOTTO_RANK_NUMBER + INITIAL_NUMBER; i++) {
            int checkLottoRank = checkSingleLottoNumber(countSameLottoNumber, i);
            lottoRank.add(checkLottoRank);
        }
    }

    public List<Integer> getLottoRank() {
        return lottoRank;
    }
}
