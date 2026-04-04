package lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
public class LottoDrawTest {
    @Test
    void 모든_숫자가_일치하면_1등이다() {
        Lotto drawn = createLotto(1, 2, 3, 4, 5, 6);
        Lotto given = createLotto(1, 2, 3, 4, 5, 6);
        LottoReceipt receipt = new LottoReceipt(List.of(given), 1000);

        assertThat(lottoDrawNumberCounts(new LottoDraw(drawn, receipt)))
                .isEqualTo(Collections.singletonMap(LottoResult.SIX, 1));
    }

    @Test
    void 숫자가_5개_일치하면_2등이다() {
        Lotto drawn = createLotto(1, 2, 3, 4, 5, 6);
        Lotto given = createLotto(1, 2, 3, 5, 6, 10);
        LottoReceipt receipt = createReceipt(given);

        assertThat(lottoDrawNumberCounts(new LottoDraw(drawn, receipt)))
                .isEqualTo(Collections.singletonMap(LottoResult.FIVE, 1));
    }

    @Test
    void 숫자가_4개_일치하면_3등이다() {
        Lotto drawn = createLotto(1, 2, 3, 5, 6, 35);
        Lotto given = createLotto(2, 3, 5, 6, 10, 45);
        LottoReceipt receipt = createReceipt(given);

        assertThat(lottoDrawNumberCounts(new LottoDraw(drawn, receipt)))
                .isEqualTo(Collections.singletonMap(LottoResult.FOUR, 1));
    }

    @Test
    void 숫자가_3개_일치하면_4등이다() {
        Lotto drawn = createLotto(1, 2, 3, 5, 6, 35);
        Lotto given = createLotto(2, 3, 5, 8, 10, 45);
        LottoReceipt receipt = createReceipt(given);

        assertThat(lottoDrawNumberCounts(new LottoDraw(drawn, receipt)))
                .isEqualTo(Collections.singletonMap(LottoResult.THREE, 1));
    }

    @Test
    void 개수가_() {
        Lotto drawn = createLotto(5, 10, 15, 20, 25, 30);
        LottoReceipt receipt = createReceipt(
                createLotto(1, 2, 3, 4, 6, 7), // 0개
                createLotto(1, 2, 3, 4, 5, 6), // 1개
                createLotto(5, 10, 11, 12, 24, 35) // 2개
        );

        assertThat(lottoDrawNumberCounts(new LottoDraw(drawn, receipt)))
                .isEqualTo(Collections.singletonMap(LottoResult.NONE, 3));
    }

    private Lotto createLotto(int... numbers) {
        List<LottoNumber> numberList = Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .toList();

        return new Lotto(numberList);
    }

    private LottoReceipt createReceipt(Lotto... lottoRows) {
        return new LottoReceipt(List.of(lottoRows), lottoRows.length * 1000);
    }

    private Map<LottoResult, Integer> lottoDrawNumberCounts(LottoDraw draw) {
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
