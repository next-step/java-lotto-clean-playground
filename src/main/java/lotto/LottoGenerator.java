package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LottoGenerator {
    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public static Lottos generateLottos(int purchaseCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int count = 0; count < purchaseCount; count++) {
            lottos.add(generateLotto());
        }
        return new Lottos(lottos);
    }

    public static Lotto generateLotto() {
        List<LottoNumber> numbers = createNumbers();

        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>(numbers.subList(0, LOTTO_NUMBER_COUNT));

        lottoNumbers.sort(Comparator.comparingInt(LottoNumber::getValue));
        return new Lotto(lottoNumbers);
    }

    private static List<LottoNumber> createNumbers() {
        List<LottoNumber> numbers = new ArrayList<>();

        for (int number = MINIMUM_LOTTO_NUMBER; number <= MAXIMUM_LOTTO_NUMBER; number++) {
            numbers.add(new LottoNumber(number));
        }
        return numbers;
    }

}
