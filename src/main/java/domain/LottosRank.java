package domain;

import java.util.ArrayList;
import java.util.List;

public class LottosRank {

    private static final int INITIAL_NUMBER = 1;
    private static final int START_RANK_NUMBER = 3;
    private static final int LAST_RANK_NUMBER = 6;
    private final List<Integer> lastWeekLottoNumber;
    private final List<Integer> rankLottos;
    private final Lottos lottos;

    public LottosRank(Lottos lottos, List<Integer> lastWeekLottoNumber) {
        this.lottos = lottos;
        this.lastWeekLottoNumber = lastWeekLottoNumber;
        this.rankLottos = makeLottosRank();
    }

    public List<Integer> getRankLottos() {
        return rankLottos;
    }

    private List<Integer> makeLottosRank() {
        List<Integer> correspondingLottoNumber = checkCorrespondingLottoNumber();
        List<Integer> rankLottos = new ArrayList<>();
        for (int i = START_RANK_NUMBER; i < LAST_RANK_NUMBER + INITIAL_NUMBER; i++) {
            int rankNumber = i;
            long countRankNumber = correspondingLottoNumber.stream().filter(c -> c == rankNumber).count();
            rankLottos.add((int) countRankNumber);
        }
        return rankLottos;
    }

    private List<Integer> checkCorrespondingLottoNumber() {
        List<Lotto> lottosBundle = lottos.getLottos();
        List<Integer> correspondingLottoNumber = new ArrayList<>();
        for (Lotto lotto : lottosBundle) {
            LottoRank lottoRank = new LottoRank(lotto, lastWeekLottoNumber);
            correspondingLottoNumber.add(lottoRank.getSameLottoNumber());
        }
        return correspondingLottoNumber;
    }
}
