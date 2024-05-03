package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_MAX_NUM = 45;
    private static final int LOTTO_SIZE = 6;

    public static List<Lotto> generateLotto(int totalPrice) {
        List<Integer> numbers = getDefaultNumbers();
        List<Lotto> lottos = new ArrayList<>();
        final int lottoCount = totalPrice / LOTTO_PRICE;
        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = new Lotto(generateRandomNumbers(numbers));
            lottos.add(lotto);
        }
        return lottos;
    }

    private static List<Integer> getDefaultNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= LOTTO_MAX_NUM; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    private static Numbers generateRandomNumbers(final List<Integer> numbers) {
        Collections.shuffle(numbers);
        List<Integer> values = new ArrayList<>(numbers.subList(0, LOTTO_SIZE));
        Collections.sort(values);
        return new Numbers(values);
    }

}
