package domain;

import java.util.*;
import java.util.stream.IntStream;

public class LottoMachine {
    private final static int LOTTO_SIZE = 6;

    public static Lotto getRandomLotto() {
        List<LottoNumber> numbers = new ArrayList<>();

        while (numbers.size() < LOTTO_SIZE) {
            LottoNumber randomNumber = LottoNumber.getRandomLottoNumber();
            putIfAbsent(numbers, randomNumber);
        }

        Collections.sort(numbers, Comparator.comparing(LottoNumber::getValue));

        return new Lotto(numbers);
    }

    private static void putIfAbsent(List<LottoNumber> numbers, LottoNumber number) {
        if (!numbers.contains(number)) {
            numbers.add(number);
        }
    }

    public static Lottos createLottos(LottoCount lottoCount) {
        return new Lottos(IntStream.range(0, lottoCount.getCount())
                .mapToObj(i -> LottoMachine.getRandomLotto())
                .toList());
    }

}
