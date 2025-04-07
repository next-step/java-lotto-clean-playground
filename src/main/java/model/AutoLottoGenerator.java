package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AutoLottoGenerator {

    private static final int LOTTO_MIN=1;
    private static final int LOTTO_MAX=45;
    private static final int NUMBER_COUNT=6;

    private static final List<Integer> ALL_NUMBERS;

    static {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LOTTO_MIN; i <= LOTTO_MAX; i++) {
            numbers.add(i);
        }
        ALL_NUMBERS = Collections.unmodifiableList(numbers);
    }

    public List<Lotto> generate(int count) {
        List<Lotto> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            result.add(generateOneLottoTicket());
        }
        return result;
    }

    private Lotto generateOneLottoTicket() {
        List<Integer> copy = new ArrayList<>(ALL_NUMBERS);
        Collections.shuffle(copy);
        List<LottoNumber> selected = copy.subList(0, NUMBER_COUNT).stream()
                .map(LottoNumber::valueOf)
                .sorted()
                .toList();

        return new Lotto(selected, LottoType.AUTO);    }
}
