import domain.LottoGenerator;
import domain.LottoNumberGenerator;
import domain.LottoTicketGenerator;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoNumberGenerator numberGenerator = new LottoNumberGenerator();
        LottoTicketGenerator ticketGenerator = new LottoTicketGenerator();
        LottoGenerator lottoGenerator = new LottoGenerator(numberGenerator, ticketGenerator);

        LottoRunner runner = new LottoRunner(inputView, outputView, lottoGenerator);
        runner.run();
    }
}
