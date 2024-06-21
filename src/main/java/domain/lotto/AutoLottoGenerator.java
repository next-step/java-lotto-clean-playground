package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoGenerator implements LottoGenerator {
    
    private static final int LOTTO_START_NUMBER = 1;
    private static final int LOTTO_END_NUMBER = 46;
    private static final int LOTTO_SIZE_LIMIT = 6;

    private static final List<Integer> lottoNumbersRange;

    static {
        lottoNumbersRange = IntStream
                .range(LOTTO_START_NUMBER, LOTTO_END_NUMBER)
                .boxed()
                .collect(Collectors.toList());
    }
    
    public LottoTicket issue() {
        Collections.shuffle(lottoNumbersRange);
        List<Integer> numbers = new ArrayList<>(lottoNumbersRange.subList(0, LOTTO_SIZE_LIMIT));
        return new LottoTicket(numbers);
    }
}