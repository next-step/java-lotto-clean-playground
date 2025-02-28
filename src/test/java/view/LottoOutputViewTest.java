package view;

import domain.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LottoOutputViewTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private LottoOutputView lottoOutputView;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        lottoOutputView = new LottoOutputView();
    }

    @Test
    @DisplayName("로또 구매 개수 출력")
    void shouldPrintLottoAmount() {
        LottoCount lottoCount = new LottoCount(5);
        lottoOutputView.printLottoAmount(lottoCount);

        String expectedOutput = "\n5개를 구매했습니다.\n";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }
    @Test
    @DisplayName("로또 구매 결과 출력")
    void shouldPrintLottoPurchaseResult() {
        LottoCount manualCount = new LottoCount(2);
        LottoCount totalCount = new LottoCount(5);
        Lottos lottos = new Lottos(List.of(
                new Lotto(List.of(LottoNumber.of(1), LottoNumber.of(2), LottoNumber.of(3), LottoNumber.of(4), LottoNumber.of(5), LottoNumber.of(6))),
                new Lotto(List.of(LottoNumber.of(7), LottoNumber.of(8), LottoNumber.of(9), LottoNumber.of(10), LottoNumber.of(11), LottoNumber.of(12)))
        ));

        lottoOutputView.printLottoPurchaseResult(manualCount, totalCount, lottos);

        String expectedOutput = "\n수동으로 2장, 자동으로 3개를 구매했습니다.\n";
        assertTrue(outputStream.toString().contains(expectedOutput));
    }

}
