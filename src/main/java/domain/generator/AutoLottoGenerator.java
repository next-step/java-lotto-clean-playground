package domain.generator;

import domain.Lotto;
import domain.LottoNumber;

import java.util.*;

public class AutoLottoGenerator implements LottoGenerator {
    //로또를 생성하는 역할
    private static final int LOTTO_MIN = 1;
    private static final int LOTTO_MAX = 45;
    private static final int PICK_COUNT = 6;

    @Override
    public Lotto generate() {
        List<LottoNumber> numbers = generateShuffledNumbers();
        List<LottoNumber> picked = pickNumbers(numbers);
        return new Lotto(picked);
    }

    private List<LottoNumber> generateShuffledNumbers() {
        List<LottoNumber> numbers = new ArrayList<>();
        for (int i = LOTTO_MIN; i <= LOTTO_MAX; i++) {
            numbers.add(new LottoNumber(i));
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    private List<LottoNumber> pickNumbers(List<LottoNumber> numbers) {
        List<LottoNumber> selected = new ArrayList<>(numbers.subList(0, PICK_COUNT));
        selected.sort(Comparator.comparingInt(LottoNumber::getNumber));
        return selected;
    }

}

