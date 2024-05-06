package service;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLottoGenerator {
    public static void makeAutomaticLottos(int automaticLottoNum, Lottos lottos) {
        for (int i = 0; i < automaticLottoNum; i++) {
            lottos.addLotto(new Lotto(makeLotto()));
        }
    }

    public static List<LottoNumber> makeLotto() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        List<Integer> selectedNumbers = numbers.subList(0, 6);
        Collections.sort(selectedNumbers);

        List<LottoNumber> lotto = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lotto.add(new LottoNumber(selectedNumbers.get(i)));
        }
        return lotto;
    }

}
