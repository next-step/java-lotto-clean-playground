package domain.lotto;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public static Lottos createLottos(PurchaseCount purchaseCount, List<Lotto> manualLottos) {
        List<Lotto> lottos = new ArrayList<>(manualLottos);
        for (int i = 0; i < purchaseCount.autoCount(); i++) {
            lottos.add(LottoGenerator.generateLotto());
        }
        return new Lottos(lottos);
    }

    public WinningResult matchRanks(WinningLotto winningLotto) {
        List<Rank> ranks = new ArrayList<>();

        for (Lotto lotto : lottos) {
            int matchCount = lotto.matchCount(winningLotto.getLotto());
            boolean bonusMatched = lotto.contains(winningLotto.getBonusNumber());
            Rank rank = Rank.findByMatchCount(matchCount, bonusMatched);
            ranks.add(rank);
        }
        return new WinningResult(ranks);
    }

    public int size() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return List.copyOf(lottos);
    }
}
