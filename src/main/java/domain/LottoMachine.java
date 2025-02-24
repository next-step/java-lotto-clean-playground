package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    private static void putIfAbsent(List<LottoNumber> numbers, LottoNumber randomNumber) {
        if (!numbers.contains(randomNumber)) {
            numbers.add(randomNumber);
        }
    }

}
