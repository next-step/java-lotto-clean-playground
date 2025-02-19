package domain;

import java.util.*;

public class Lotto {

    private final static int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers = new ArrayList<>();

    public List<LottoNumber> getLottoNumbers() {
        return List.copyOf(lottoNumbers);
    }

    public static Lotto getRandomLotto() {
        Lotto lotto = new Lotto();

        while (lotto.lottoNumbers.size() < LOTTO_SIZE) {
            lotto.putIfAbsent(LottoNumber.getRandomLottoNumber());
        }

        Collections.sort(lotto.lottoNumbers, Comparator.comparing(LottoNumber::getValue));

        return lotto;
    }

    private void putIfAbsent(LottoNumber lottoNumber) {
        if (!lottoNumbers.contains(lottoNumber)) {
            lottoNumbers.add(lottoNumber);
        }
    }

}
