package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private static final int PRICE = 1000;
    private static final int LOTTO_UPPER_BOUND = 45;
    private static final List<Integer> LOTTO_NUMBERS_CACHE = new ArrayList<>();

    private final List<Lotto> lottos;

    static {
        for (int i = 0; i < LOTTO_UPPER_BOUND; i++) {
            LOTTO_NUMBERS_CACHE.add(i, i + 1);
        }
    }

    public Lottos(final int purchaseAmount) {
        this.lottos = generateLottos(purchaseAmount / PRICE);
    }

    private List<Lotto> generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = getSingleLotto();
            lottos.add(lotto);
        }
        return lottos;
    }

    private Lotto getSingleLotto() {
        List<Integer> lottoNumbers = new ArrayList<>(LOTTO_NUMBERS_CACHE);
        Collections.shuffle(lottoNumbers);
        List<Integer> subNumbers = new ArrayList<>(lottoNumbers.subList(0, 6));
        Collections.sort(subNumbers);
        return new Lotto(subNumbers);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
