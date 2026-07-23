package view;

import static org.assertj.core.api.Assertions.assertThat;

import domain.Lotto;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OutputViewTest {
    private final PrintStream standardOutput = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @AfterEach
    void restoreOutput() {
        System.setOut(standardOutput);
    }

    @Test
    @DisplayName("구매한 로또 개수와 번호를 출력한다")
    void printPurchasedLottoTickets() {
        OutputView outputView = new OutputView();
        List<Lotto> purchasedLottoTickets = createLottoTickets();
        System.setOut(new PrintStream(outputStream));

        outputView.printPurchasedLottoTickets(purchasedLottoTickets);

        assertThat(outputStream.toString()).contains("2개를 구매했습니다.");
        assertThat(outputStream.toString()).contains("[1, 2, 3, 4, 5, 6]");
    }

    private List<Lotto> createLottoTickets() {
        return List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),
                new Lotto(List.of(7, 8, 9, 10, 11, 12))
        );
    }
}
