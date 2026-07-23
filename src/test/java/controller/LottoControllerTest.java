package controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoControllerTest {
    private final InputStream standardInput = System.in;
    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @AfterEach
    void restoreConsole() {
        System.setIn(standardInput);
        System.setOut(standardOutput);
    }

    @Test
    @DisplayName("입력한 금액만큼 로또를 구매하고 출력한다")
    void buyAndPrintLottosByInputAmount() {
        System.setIn(new ByteArrayInputStream("3000".getBytes()));
        System.setOut(new PrintStream(outputStream));
        LottoController lottoController = new LottoController();

        lottoController.run();

        assertThat(outputStream.toString()).contains("구입금액을 입력해 주세요.");
        assertThat(outputStream.toString()).contains("3개를 구매했습니다.");
    }
}
