package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int LOTTO_PRICE = 1000;

    private static final List<Integer> NUMBERS = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                                                        .boxed()
                                                        .toList();

    public Lottos generateLottos(Price price) {
        final int numberOfLotto = getNumberOfLotto(price);

        Lottos lottos = new Lottos();
        for (int count = 0; count < numberOfLotto; count++) {
            final List<Integer> numbers = getNumbers();
            final Lotto lotto = new Lotto(numbers);
            lottos.addLotto(lotto);
        }
        return lottos;
    }

    private int getNumberOfLotto(Price price) {
        return price.price() / LOTTO_PRICE;
    }

    private List<Integer> getNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>(NUMBERS);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, Lotto.SIZE);
    }

}
