package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public List<Lotto> generate(int lottoCount) {
        List<Lotto> lottos = new ArrayList<Lotto>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generateSingleLotto());
        }
        return lottos;
    }

    private Lotto generateSingleLotto() {
        List<Integer> allNumbers = createShuffledLottoNumbers();
        List<Integer> selected = pickFirstSixAndSort(allNumbers);
        return new Lotto(selected);
    }

    private List<Integer> createShuffledLottoNumbers() {
        List<Integer> allNumbers = new ArrayList<>();
        for (int i = LOTTO_MIN; i <= LOTTO_MAX; i++) {
            allNumbers.add(i);
        }
        Collections.shuffle(allNumbers);
        return allNumbers;
    }

    private List<Integer> pickFirstSixAndSort(List<Integer> numbers) {
        List<Integer> selected = new ArrayList<>(numbers.subList(0, LOTTO_NUMBER_COUNT));
        Collections.sort(selected);
        return selected;
    }

}
