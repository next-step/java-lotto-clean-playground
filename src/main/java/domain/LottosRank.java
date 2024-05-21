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

    private List<Integer> makeLottosRank(){
        List<Integer> correspondingLottoList = makeCorrespondingLottoList();
        List<Integer> rankLottos = new ArrayList<>();
        for(int i=START_RANK_NUMBER;i<LAST_RANK_NUMBER+INITIAL_NUMBER;i++){
            int rankNumber = i;
            long countRankNumber = correspondingLottoList.stream().filter(c -> c == rankNumber).count();
            rankLottos.add((int) countRankNumber);
        }
        return rankLottos;
    }

    private List<Integer> makeCorrespondingLottoList(){
        List<Lotto> lottoList = lottos.getLottos();
        List<Integer> correspondingLottoList = new ArrayList<>();
        for(Lotto lotto : lottoList){
            LottoRank lottoRank = new LottoRank(lotto,lastWeekLottoNumber);
            correspondingLottoList.add(lottoRank.getSameLottoNumber());
        }
        return correspondingLottoList;
    }

    public List<Integer> getRankLottos(){
        return rankLottos;
    }
}
