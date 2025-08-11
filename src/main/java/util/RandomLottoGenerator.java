package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Lotto;
import model.LottoNumber;

public class RandomLottoGenerator implements LottoGenerator {
    private final List<Integer> numberList = new ArrayList<>();

    public RandomLottoGenerator() {
        for (int i = 1; i <= 45; i++) {
            numberList.add(i);
        }
    }

    @Override
    public Lotto generate() {
        Collections.shuffle(numberList);

        List<Integer> selected = new ArrayList<>(numberList.subList(0, 6));

        Collections.sort(selected);

        List<LottoNumber> lottoNumbers = selected.stream()
            .map(LottoNumber::new)
            .toList();

        return new Lotto(lottoNumbers);
    }
}
