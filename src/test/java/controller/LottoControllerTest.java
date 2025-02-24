package controller;

import domain.LottoStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.LottoService;
import util.FixNumbersGenerator;
import view.LottoOutputView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static fixture.LottoFixture.testNumbersOneToSix;
import static org.assertj.core.api.Assertions.assertThat;

class LottoControllerTest {

    private LottoStore lottoStore;
    private LottoOutputView lottoOutputView;
    private LottoService lottoService;
    private LottoController lottoController;

    @BeforeEach
    void setUp() {
        lottoStore = new LottoStore(new FixNumbersGenerator(testNumbersOneToSix));
        lottoOutputView = new LottoOutputView();
        lottoService = new LottoService(lottoStore);
        lottoController = new LottoController(lottoOutputView, lottoService);
    }

    @Test
    @DisplayName("OK : 로또 게임을 정상적으로 진행한다.")
    void playLotto() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                ("""
                        14000
                        3
                        8, 9, 10, 11, 12, 13
                        14, 15, 16, 17, 18, 19
                        20, 21, 22, 23, 24, 25
                        20, 21, 22, 40, 41, 42
                        7""").getBytes());
        System.setIn(byteArrayInputStream);
        lottoController.start();

        String output = outputStream.toString();
        assertThat(output).contains("3개 일치 (5000원)- 1개");
        assertThat(output).contains("4개 일치 (50000원)- 0개");
        assertThat(output).contains("5개 일치 (1500000원)- 0개");
        assertThat(output).contains("5개 일치, 보너스 볼 일치 (30000000원)- 0개");
        assertThat(output).contains("6개 일치 (2000000000원)- 0개");
        assertThat(output).contains("총 수익률은 0.35입니다.");
    }
}