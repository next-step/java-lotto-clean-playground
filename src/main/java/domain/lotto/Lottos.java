package domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public int size() {
        return lottos.size();
    }

    public List<Rank> matchRanks(Lotto winningLotto, LottoNumber bonusNumber) {
        List<Rank> ranks = new ArrayList<>();

        for (Lotto lotto : lottos) {
            int matchCount = lotto.matchCount(winningLotto);
            boolean bonusMatched = lotto.contains(bonusNumber);
            Rank rank = Rank.findByMatchCount(matchCount, bonusMatched);
            ranks.add(rank);
        }
        return ranks;
    }

    public List<String> toDisplayStrings() {
        List<String> displays = new ArrayList<>();

        for (Lotto lotto : lottos) {
            displays.add(lotto.getLottoNumbers().toString());
        }
        return displays;
    }
}
