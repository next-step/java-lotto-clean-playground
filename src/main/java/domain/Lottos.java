package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lottos {

    private static final int PRICE = 1000;
    private static final int LOTTO_UPPER_BOUND = 45;
    private static final int LOTTO_LOWER_BOUND = 1;
    private static final List<LottoNumber> LOTTO_NUMBERS_CACHE = new ArrayList<>();

    private final List<Lotto> lottos;

    static {
        for (int i = LOTTO_LOWER_BOUND; i <= LOTTO_UPPER_BOUND; i++) {
            LOTTO_NUMBERS_CACHE.add(new LottoNumber(i));
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
        List<LottoNumber> lottoNumbers = new ArrayList<>(LOTTO_NUMBERS_CACHE);
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> subNumbers = new ArrayList<>(lottoNumbers.subList(0, 6));
        subNumbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return new Lotto(subNumbers);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }
}
