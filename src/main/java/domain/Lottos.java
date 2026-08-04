package domain;

import java.util.EnumMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Lottos {

    List<Lotto> lottos = new ArrayList<>();

    public Lottos(int autoLottoCount, int manualLottoCount, List<String> values) {

        List<Lotto> autoLotto = AutoLotto.generateAutoLotto(autoLottoCount);
        List<Lotto> manualLotto = ManualLotto.generateManualLotto(manualLottoCount, values);

        lottos.addAll(manualLotto);
        lottos.addAll(autoLotto);
    }


    private List<Rank> findRanks(CorrectLotto correctLotto) { // 실제 로또 번호의 등수 반환
        List<Rank> ranks = new ArrayList<>();
        for(int i = 0; i < lottos.size(); i++) {
            ranks.add(lottos.get(i).findRank(correctLotto));
        }
        return ranks;
    }


    public Map<Rank, Integer> getRanksCount(CorrectLotto correctLotto) { // 등수, 개수 맵 반환
        Map<Rank, Integer> ranksCount = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values()) {
            ranksCount.put(rank, 0);
        }

        for (Rank rank : findRanks(correctLotto)) { // 이 부분은 AI의 도움을 받았습니다
            ranksCount.merge(rank, 1, Integer::sum);
        }

        return ranksCount;
    }


    public int getLottoNumberCount() { // 로또 개수 반환
        return lottos.size();
    }


    public List<Lotto> getLottos() {
        return lottos;
    }
}
