package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    public List<LottoNumbers> generateAutoNumbers(int count) {
        return java.util.stream.IntStream.range(0, count)
            .mapToObj(i -> generateSixNumbers())
            .collect(java.util.stream.Collectors.toList());
    }

    public List<LottoNumbers> mergeLottoNumbers(Lotto manualLotto, Lotto autoLotto) {
        List<LottoNumbers> allLottoNumbers = new ArrayList<>();
        allLottoNumbers.addAll(manualLotto.getNumbers());
        allLottoNumbers.addAll(autoLotto.getNumbers());
        return allLottoNumbers;
    }

    private LottoNumbers generateSixNumbers() {
        List<Integer> shuffledNumbers = createShuffledNumbers();
        List<Integer> pickSixNumber = pickRandomSixSorted(shuffledNumbers);
        return new LottoNumbers(pickSixNumber);
    }

    private List<Integer> createShuffledNumbers() {
        List<Integer> numbers = IntStream.rangeClosed(1, MAX_NUMBER)
            .boxed()
            .collect(Collectors.toList());

        Collections.shuffle(numbers);
        return numbers;
    }

    private List<Integer> pickRandomSixSorted(List<Integer> numbers) {
        return numbers.stream()
            .limit(LOTTO_SIZE)
            .sorted()
            .collect(Collectors.toList());
    }
}

