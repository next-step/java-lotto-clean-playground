package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class LottorReceiptResultTest {
    @Test
    void 모든_숫자가_일치하면_1등이다() {
        WinningLotto drawn = createWinningLotto(9, 1, 2, 3, 4, 5, 6);
        Lotto given = createLotto(1, 2, 3, 4, 5, 6);
        LottoReceipt receipt = new LottoReceipt(List.of(given), 1000);

        assertThat(lottoDrawNumberCounts(new LottoReceiptResult(drawn, receipt)))
                .isEqualTo(Collections.singletonMap(LottoResult.SIX, 1));
    }

    @Test
    void 숫자가_5개_일치하고_보너스도_일치하면_2등이다() {
        WinningLotto drawn = createWinningLotto(1, 2, 3, 4, 5, 6, 7);
        Lotto given = createLotto(1, 2, 3, 4, 6, 7);
        LottoReceipt receipt = createReceipt(given);

        assertThat(lottoDrawNumberCounts(new LottoReceiptResult(drawn, receipt)))
                .isEqualTo(Collections.singletonMap(LottoResult.FIVE_BONUS, 1));
    }

    @Test
    void 숫자가_5개_일치하면_3등이다() {
        WinningLotto drawn = createWinningLotto(11, 1, 2, 3, 4, 5, 6);
        Lotto given = createLotto(1, 2, 3, 5, 6, 10);
        LottoReceipt receipt = createReceipt(given);

        assertThat(lottoDrawNumberCounts(new LottoReceiptResult(drawn, receipt)))
                .isEqualTo(Collections.singletonMap(LottoResult.FIVE, 1));
    }

    @Test
    void 숫자가_4개_일치하면_4등이다() {
        WinningLotto drawn = createWinningLotto(32, 1, 2, 3, 5, 6, 35);
        Lotto given = createLotto(2, 3, 5, 6, 10, 45);
        LottoReceipt receipt = createReceipt(given);

        assertThat(lottoDrawNumberCounts(new LottoReceiptResult(drawn, receipt)))
                .isEqualTo(Collections.singletonMap(LottoResult.FOUR, 1));
    }

    @Test
    void 숫자가_3개_일치하면_5등이다() {
        WinningLotto drawn = createWinningLotto(15, 1, 2, 3, 5, 6, 35);
        Lotto given = createLotto(2, 3, 5, 8, 10, 45);
        LottoReceipt receipt = createReceipt(given);

        assertThat(lottoDrawNumberCounts(new LottoReceiptResult(drawn, receipt)))
                .isEqualTo(Collections.singletonMap(LottoResult.THREE, 1));
    }

    @Test
    void 숫자가_2개_이하로_일치하면_돈은_없다() {
        WinningLotto drawn = createWinningLotto(2, 5, 10, 15, 20, 25, 30);
        LottoReceipt receipt = createReceipt(
                createLotto(1, 2, 3, 4, 6, 7), // 0개
                createLotto(1, 2, 3, 4, 5, 6), // 1개
                createLotto(5, 10, 11, 12, 24, 35) // 2개
        );

        LottoReceiptResult draw = new LottoReceiptResult(drawn, receipt);

        assertThat(lottoDrawNumberCounts(draw))
                .isEqualTo(Collections.singletonMap(LottoResult.NONE, 3));
        assertThat(draw.getRateOfReturn().value())
                .isEqualTo(0f);
    }

    private WinningLotto createWinningLotto(int bonus, int... numbers) {
        LottoNumbers lottoNumbers = createLottoNumbers(numbers);
        return new WinningLotto(lottoNumbers, new LottoNumber(bonus));
    }

    private Lotto createLotto(int... numbers) {
        LottoNumbers lottoNumbers = createLottoNumbers(numbers);
        return new Lotto(lottoNumbers);
    }

    private LottoNumbers createLottoNumbers(int... numbers) {
        List<LottoNumber> numberList = Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .toList();

        return new LottoNumbers(numberList);
    }

    private LottoReceipt createReceipt(Lotto... lottoRows) {
        return new LottoReceipt(List.of(lottoRows), lottoRows.length * 1000);
    }

    private Map<LottoResult, Integer> lottoDrawNumberCounts(LottoReceiptResult draw) {
        Map<LottoResult, Integer> numberCounts = new EnumMap<>(LottoResult.class);
        for (LottoResult result : LottoResult.values()) {
            int count = draw.getCount(result);
            if (count != 0) {
                numberCounts.put(result, count);
            }
        }
        return numberCounts;
    }
}
