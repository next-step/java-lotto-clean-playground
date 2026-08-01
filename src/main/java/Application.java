import domain.LottoTicketCount;
import java.util.Scanner;
import view.InputView;
import view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoTicketCount lottoTicketCount = new LottoTicketCount();
        int lottoTicketTotalCount;
        lottoTicketTotalCount = lottoTicketCount.convertLottoPriceToTicketCount(InputView.inputLottoTotalPrice());
        OutputView.printLottoCount(lottoTicketTotalCount);



    }
}
