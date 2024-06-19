package domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;


public class LottoRankBundle {

    private static final int RESET_NUMBER = 0;
    private static final int INITIAL_NUMBER = 1;
    private static final int BONUS_COUNT = 5;

    private final List<LottoRank> lottoRank;

    public LottoRankBundle(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        this.lottoRank = makeLottoRankBundle(lottos, lastWeekLottoNumber);
    }

    public List<LottoRank> getLottoRank() {
        return lottoRank;
    }

    private List<LottoRank> makeLottoRankBundle(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        List<LottoRank> lottoRanks = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : makeLottoRank(lottos, lastWeekLottoNumber).entrySet()) {
            lottoRanks.add(new LottoRank(entry.getKey(), entry.getValue()));
        }
        return lottoRanks;
    }

    private Map<String, Integer> makeLottoRank(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        Map<String, Integer> rankLotto = checkCorrespondingLottosNumber(lottos, lastWeekLottoNumber);
        List<String> sameLottoNumbers = LottoPrice.getSameLottoNumberBundle();
        Map<String, Integer> sortedRankLotto = new HashMap<>();
        for (String sameLottoNumber : sameLottoNumbers) {
            rankLotto.put(sameLottoNumber, rankLotto.getOrDefault(sameLottoNumber, RESET_NUMBER) + RESET_NUMBER);
            sortedRankLotto.put(sameLottoNumber, rankLotto.getOrDefault(sameLottoNumber, rankLotto.get(sameLottoNumber)));
        }
        return new TreeMap<>(sortedRankLotto);
    }

    private Map<String, Integer> checkCorrespondingLottosNumber(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        Map<String, Integer> rankLotto = new HashMap<>();
        for (Lotto lotto : lottos.getLottos()) {
            List<Integer> lottoNumber = lotto.getLottoNumber().stream().map(LottoNumber::getLottoNumber).toList();
            String sameLottoNumber = checkCorrespondingLottoNumber(lottoNumber, lastWeekLottoNumber);
            rankLotto.put(sameLottoNumber, rankLotto.getOrDefault(sameLottoNumber, RESET_NUMBER) + INITIAL_NUMBER);
        }
        return rankLotto;
    }

    private String checkCorrespondingLottoNumber(List<Integer> lottoNumber, List<Integer> lastWeekLottoNumber) {
        int sameLottoNumber = (int) lastWeekLottoNumber.stream().filter(lottoNumber::contains).count();
        if (isBonusNumber(lottoNumber, lastWeekLottoNumber, sameLottoNumber)) {
            return LottoPrice.getBonusSameLottoNumber();
        }
        return Integer.toString(sameLottoNumber);
    }

    private boolean isBonusNumber(List<Integer> lottoNumber, List<Integer> lastWeekLottoNumber, int sameLottoNumber) {
        return lottoNumber.contains(lastWeekLottoNumber.get(lastWeekLottoNumber.size() - INITIAL_NUMBER)) && sameLottoNumber == BONUS_COUNT;
    }
}
