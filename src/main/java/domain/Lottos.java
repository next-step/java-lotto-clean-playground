package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lottos {

    private static final int PRICE = 1000;
    private static final int LOTTO_UPPER_BOUND = 45;
    private final List<Lotto> lottos;

    public Lottos(final int purchaseAmount) {
        this.lottos = generateLottos(purchaseAmount / PRICE);
    }

    public List<Lotto> getLottos() {
        return lottos;
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
        ArrayList<Integer> lottoNumbers = generateLottoNumbersArray();
        Collections.shuffle(lottoNumbers);
        List<Integer> subNumbers = lottoNumbers.subList(0, 6);
        Collections.sort(subNumbers);
        return new Lotto(subNumbers);
    }

    private ArrayList<Integer> generateLottoNumbersArray() {
        ArrayList<Integer> lottoNumbers = new ArrayList<>(LOTTO_UPPER_BOUND);
        for (int i = 0; i < LOTTO_UPPER_BOUND; i++) {
            lottoNumbers.add(i, i + 1);
        }
        return lottoNumbers;
    }
}
