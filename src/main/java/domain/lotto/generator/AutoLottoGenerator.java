package domain.lotto.generator;

import domain.lotto.IssuanceType;
import domain.lotto.LottoNumber;
import domain.lotto.LottoTicket;
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

    @Override
    public boolean supports(IssuanceType issuanceType) {
        return issuanceType == IssuanceType.AUTO;
    }

    public LottoTicket issue() {
        Collections.shuffle(lottoNumbersRange);
        final List<LottoNumber> numbers = lottoNumbersRange.subList(0, LOTTO_SIZE_LIMIT)
            .stream()
            .map(LottoNumber::new)
            .collect(Collectors.toList());
        return new LottoTicket(numbers);
    }
}
