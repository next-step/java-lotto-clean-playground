package model;

import global.Rank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public Lottos(final List<Lotto> manual, final List<Lotto> auto) {
        manual.addAll(auto);
        this.lottos = manual;
    }

    public static Lottos forRandomGenerate(final LottoPurchaseMoney lottoPurchaseMoney, final ManualBuyCount manualBuyCount) {
        final List<Lotto> lottos = new ArrayList<>();

        int autoGenerateCount = lottoPurchaseMoney.getPurchaseQuantity() - manualBuyCount.getCount();
        for (int i = 0; i < autoGenerateCount; i++) {
            lottos.add(Lotto.byRandomGenerate());
        }

        return new Lottos(lottos);
    }

    public static Lottos forManualInput(final List<String[]> manualInput) {
        List<Lotto> lottos = manualInput.stream()
                .map(Lotto::fromStringsInput)
                .collect(Collectors.toList());

        return new Lottos(lottos);
    }

    public LottoResult getResult(final Lotto winningLotto, final LottoNumber bonusNumber) {
        final Map<Rank, Integer> result = initMap();
        for (Lotto lotto : lottos) {
            final Rank rank = lotto.getRank(winningLotto, bonusNumber);
            result.put(rank, result.get(rank) + 1);
        }

        return new LottoResult(result);
    }

    private Map<Rank, Integer> initMap() {
        final Map<Rank, Integer> result = new HashMap<>();
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        return result;
    }

    public int getBuyLottoCount() {
        return lottos.size();
    }

    public List<Lotto> getLottos() {
        return new ArrayList<>(lottos);
    }
}
