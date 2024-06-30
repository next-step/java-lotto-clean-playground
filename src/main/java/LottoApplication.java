import controller.LottoController;
import domain.LottoMachine;
import domain.RandomNumberGenerator;
import dto.LottoPaperDto;
import dto.LottoResultDto;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        final LottoController lottoController = new LottoController();
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();

        int price = inputView.getPrice();
        LottoPaperDto lottoPaper = lottoController.buyLotto(price);
        outputView.printRowNumber(lottoPaper.getRowNum());
        outputView.printPaper(lottoPaper);

        List<Integer> answer = inputView.getAnswer();
        LottoResultDto lottoResult = lottoController.checkLotto(lottoPaper, answer);
        outputView.printResult(lottoResult);
        outputView.printRewardRate(lottoResult);
    }
}
