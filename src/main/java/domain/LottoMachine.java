package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoMachine {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public int calculateNumberOfLottos(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;
    }

    public List<Lotto> generateAutomaticLottos(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateRandomLotto());
        }
        return lottos;
    }

    private Lotto generateRandomLotto() {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < LOTTO_NUMBER_COUNT) {
            int number = random.nextInt(45) + 1;
            if (!numbers.contains(number)) {
                numbers.add(number);
            }
        }
        return new Lotto(numbers);
    }
}
