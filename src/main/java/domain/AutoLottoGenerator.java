package domain;

import java.util.*;

public class AutoLottoGenerator implements LottoGenerator {
    //로또를 생성하는 역할
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final int PICK_COUNT = 6;

    @Override
    public Lotto generate() {
        List<Integer> numbers = generateShuffledNumbers();
        List<Integer> picked = pickNumbers(numbers);
        return new Lotto(picked);
    }

    private List<Integer> generateShuffledNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = LOTTO_MIN; i <= LOTTO_MAX; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    private List<Integer> pickNumbers(List<Integer> numbers) {
        List<Integer> selected = new ArrayList<>(numbers.subList(0, PICK_COUNT));
        Collections.sort(selected);
        return selected;
    }

}

