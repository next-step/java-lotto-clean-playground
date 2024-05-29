package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoRank {

    private static final int RESET_NUMBER = 0;
    private static final int INITIAL_NUMBER = 1;
    private static final int BONUS_COUNT = 5;

    private final Map<String, Integer> lottoRank;

    public LottoRank(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        this.lottoRank = makeLottoRank(lottos, lastWeekLottoNumber);
    }

    public Map<String, Integer> getLottoRank() {
        return lottoRank;
    }

    private Map<String, Integer> makeLottoRank(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        Map<String, Integer> rankLotto = checkCorrespondingLottosNumber(lottos, lastWeekLottoNumber);
        List<String> sameLottoNumbers = LottoPrice.getSameLottoNumberBundle();
        for (String sameLottoNumber : sameLottoNumbers) {
            rankLotto.put(sameLottoNumber, rankLotto.getOrDefault(sameLottoNumber, RESET_NUMBER) + RESET_NUMBER);
        }
        return rankLotto;
    }

    private Map<String, Integer> checkCorrespondingLottosNumber(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        Map<String, Integer> rankLotto = new HashMap<>();
        for (Lotto lotto : lottos.getLottos()) {
            String sameLottoNumber = checkCorrespondingLottoNumber(lotto.getLottoNumber(), lastWeekLottoNumber);
            rankLotto.put(sameLottoNumber, rankLotto.getOrDefault(sameLottoNumber, RESET_NUMBER) + INITIAL_NUMBER);
        }
        return rankLotto;
    }

    private String checkCorrespondingLottoNumber(List<Integer> lottoNumber, List<Integer> lastWeekLottoNumber) {
        int sameLottoNumber = RESET_NUMBER;
        for (int elementOfLastWeekLottoNumber : lastWeekLottoNumber) {
            if (lottoNumber.contains(elementOfLastWeekLottoNumber)) {
                sameLottoNumber++;
            }
        }
        if (checkBonusNumber(lottoNumber, lastWeekLottoNumber, sameLottoNumber)) {
            return LottoPrice.getBonusSameLottoNumber();
        }
        return Integer.toString(sameLottoNumber);
    }

    private boolean checkBonusNumber(List<Integer> lottoNumber, List<Integer> lastWeekLottoNumber, int sameLottoNumber) {
        return lottoNumber.contains(lastWeekLottoNumber.get(lastWeekLottoNumber.size() - INITIAL_NUMBER)) && sameLottoNumber == BONUS_COUNT;
    }
}
