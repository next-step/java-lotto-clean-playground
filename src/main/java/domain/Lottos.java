package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Lottos {

    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_UPPER_BOUND = 45;
    private final List<Lotto> lottos;

    public Lottos(final PurchaseAmount purchaseAmount) {
        this.lottos = generateLottos(purchaseAmount.getLottoCount());
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
    }

    private List<Lotto> generateLottos(int lottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(getSingleLotto()));
        }
        return lottos;
    }

    private List<LottoNumber> getSingleLotto() {
        List<LottoNumber> lottoNumbers = generateLottoNumbersArray();
        Collections.shuffle(lottoNumbers);
        List<LottoNumber> subNumbers = new ArrayList<>(lottoNumbers.subList(0, LOTTO_SIZE));
        subNumbers.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return subNumbers;
    }

    private List<LottoNumber> generateLottoNumbersArray() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= LOTTO_UPPER_BOUND; i++) {
            lottoNumbers.add(new LottoNumber(i));
        }
        return lottoNumbers;
    }
}