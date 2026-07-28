package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    public Lotto generateLotto() {
        List<Integer> numbers = generateNumbers();
        Collections.shuffle(numbers);

        List<Integer> selected = new ArrayList<>(numbers.subList(0, Lotto.LOTTO_SIZE));
        Collections.sort(selected);

        List<LottoNumber> lottoNumbers = selected.stream()
                .map(LottoNumber::new)
                .toList();

        return new Lotto(lottoNumbers);
    }

    private List<Integer> generateNumbers() {
        List<Integer> allNumbers = new ArrayList<>();
        for (int i = LottoNumber.MIN_LOTTO_NUMBER; i <= LottoNumber.MAX_LOTTO_NUMBER; i++) {
            allNumbers.add(i);
        }

        return allNumbers;
    }
}
