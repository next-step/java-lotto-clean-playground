package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoRank {

    private static final int RESET_NUMBER = 0;
    private static final int INITIAL_NUMBER = 1;
    private static final int LAST_RANKER = 3;
    private static final int FIRST_RANKER = 6;

    private final List<Integer> lottoRank;

    public LottoRank(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        this.lottoRank = makeLottoRank(lottos, lastWeekLottoNumber);
    }

    public List<Integer> getLottoRank() {
        return lottoRank;
    }

    private List<Integer> makeLottoRank(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        Map<Integer, Integer> rankLotto = checkCorrespondingLottosNumber(lottos, lastWeekLottoNumber);
        List<Integer> realRankLotto = new ArrayList<>();
        for (int i = LAST_RANKER; i < FIRST_RANKER + INITIAL_NUMBER; i++) {
            saveRank(rankLotto, i, realRankLotto);
        }
        System.out.println(realRankLotto);
        return realRankLotto;
    }

    private void saveRank(Map<Integer, Integer> rankLotto, int rankNumber, List<Integer> realRankLotto) {
        if (!rankLotto.containsKey(rankNumber)) {
            realRankLotto.add(RESET_NUMBER);
        }
        if (rankLotto.containsKey(rankNumber)) {
            realRankLotto.add(rankLotto.get(rankNumber));
        }
    }

    private Map<Integer, Integer> checkCorrespondingLottosNumber(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        Map<Integer, Integer> rankLotto = new HashMap<>();
        for (Lotto lotto : lottos.getLottos()) {
            int sameLottoNumber = checkCorrespondingLottoNumber(lotto.getLottoNumber(), lastWeekLottoNumber);
            rankLotto.put(sameLottoNumber, rankLotto.getOrDefault(sameLottoNumber, RESET_NUMBER) + INITIAL_NUMBER);
        }
        System.out.println(rankLotto);
        return rankLotto;
    }

    private int checkCorrespondingLottoNumber(List<Integer> lottoNumber, List<Integer> lastWeekLottoNumber) {
        int sameLottoNumber = RESET_NUMBER;
        for (int elementOfLastWeekLottoNumber : lastWeekLottoNumber) {
            if (lottoNumber.contains(elementOfLastWeekLottoNumber)) {
                sameLottoNumber++;
            }
        }
        return sameLottoNumber;
    }
}
