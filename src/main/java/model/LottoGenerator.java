package model;

import java.util.*;

public class LottoGenerator {

    private static final List<Integer> NUMBERS = Arrays.asList(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
            31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45);

    public Lottos generateRandomLotto(final Money money) {
        final List<Lotto> lottos = new ArrayList<>();
        Collections.shuffle(NUMBERS);
        Random random = new Random();
        for (int i = 0; i < money.getPurchaseQuantity(); i++) {
            final int randomNumber = random.nextInt(0, 40);
            lottos.add(new Lotto(NUMBERS.subList(randomNumber, randomNumber + 6)));
        }
        return new Lottos(lottos);
    }
}
