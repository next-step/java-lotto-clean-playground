package domain.util;

import domain.lotto.LottoNumber;
import domain.lotto.LottoTicket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoUtil {

    public static LottoTicket createLottoTicket(Integer... numbers) {
        final List<LottoNumber> lottoNumbers = Arrays.stream(numbers)
            .map(LottoNumber::new)
            .collect(Collectors.toList());

        return new LottoTicket(lottoNumbers);
    }
}
