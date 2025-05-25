package lotto;

import lotto.domain.InputLottoNumber;
import lotto.domain.LottoNumber;

import java.util.List;
import java.util.stream.IntStream;

public class LottoCounter {

    public static List<InputLottoNumber> generateAuto(int totalTicket, int passivityCount) {
        int autoLottoCount = totalTicket - passivityCount;
        return IntStream.range(0, autoLottoCount)
                .mapToObj(i -> {
                    List<Integer> pool = LottoManage.pullOutNumbers();
                    LottoManage.shuffleNumbers(pool);
                    List<LottoNumber> picked = LottoManage.pickupLottoNumbers(pool)
                            .stream().map(LottoNumber::new).toList();
                    return InputLottoNumber.of(picked);
                })
                .toList();
    }
}
