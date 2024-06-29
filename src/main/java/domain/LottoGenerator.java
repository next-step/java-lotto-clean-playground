package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final List<Integer> NUMBERS = IntStream.rangeClosed(Lotto.MIN_NUMBER, Lotto.MAX_NUMBER)
                                                        .boxed()
                                                        .toList();

    public Lottos generateLottos(PurchasePrice purchasePrice) {
        final int numberOfLotto = getNumberOfLotto(purchasePrice);

        Lottos lottos = new Lottos();
        for (int count = 0; count < numberOfLotto; count++) {
            final List<Integer> numbers = getNumbers();
            final Lotto lotto = new Lotto(numbers);
            lottos.addLotto(lotto);
        }
        return lottos;
    }

    private int getNumberOfLotto(PurchasePrice purchasePrice) {
        return purchasePrice.price() / Lotto.PRICE;
    }

    private List<Integer> getNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>(NUMBERS);
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.subList(0, Lotto.SIZE);
    }

}
