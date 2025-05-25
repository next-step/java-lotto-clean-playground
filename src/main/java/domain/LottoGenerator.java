package domain;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGenerator {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    // 최초 1회 생성되는 불변 리스트
    private static final List<Integer> BASE_NUMBERS;

    static {
        List<Integer> base = new ArrayList<>();
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            base.add(i);
        }
        BASE_NUMBERS = Collections.unmodifiableList(base);
    }

    public Lotto generate() {
        return new Lotto(generateSingleLotto());
    }

    public List<Lotto> generate(int count) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generate());
        }
        return lottos;
    }

    private List<LottoNumber> generateSingleLotto() {
        List<Integer> copy = new ArrayList<>(BASE_NUMBERS);
        Collections.shuffle(copy);
        List<Integer> selected = new ArrayList<>(copy.subList(0, LOTTO_SIZE));
        Collections.sort(selected);
        return selected.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
