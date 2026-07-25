package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = new ArrayList<>(lottos);
    }

    public PurchasedLottoNumbers toNumbers() {
        List<List<Integer>> numbers = new ArrayList<>();
        for (Lotto lotto : lottos) {
            numbers.add(lotto.getLottoNumbers());
        }
        return new PurchasedLottoNumbers(numbers);
    }

    public MatchingNumberCounts countMatches(WinningNumbers winningNumbers) {
        List<Integer> matchingNumberCounts = new ArrayList<>();
        for (Lotto lotto : lottos) {
            matchingNumberCounts.add(winningNumbers.countMatches(lotto));
        }
        return new MatchingNumberCounts(matchingNumberCounts);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
