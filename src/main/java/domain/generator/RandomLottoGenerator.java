package domain.generator;

import domain.Lotto;
import domain.LottoNumber;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGenerator implements LottoGenerator {

    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    @Override
    public Lotto generate() {

        List<Integer> numberPool = generateNumberPool();

        Collections.shuffle(numberPool);

        List<LottoNumber> selectedNumbers = numberPool.subList(0, LOTTO_NUMBER_COUNT)
                .stream()
                .sorted()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return new Lotto(selectedNumbers);
    }

    private List<Integer> generateNumberPool() {
        return IntStream.rangeClosed(LOTTO_START_NUMBER, LOTTO_END_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }
}
