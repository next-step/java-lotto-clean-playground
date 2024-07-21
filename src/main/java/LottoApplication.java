import controller.LottoController;
import domain.LottoAnswer;
import domain.LottoPrice;
import dto.LottoPaperDto;
import dto.LottoResultDto;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        final LottoController lottoController = new LottoController();
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        LottoPrice price = new LottoPrice(inputView.getPrice());
        LottoPaperDto lottoPaper = lottoController.buyLotto(price);
        outputView.printRowNumber(lottoPaper.getRowNum());
        outputView.printPaper(lottoPaper);

        LottoAnswer lottoAnswer = new LottoAnswer(inputView.getAnswer(), inputView.getBonus());
        LottoResultDto lottoResult = lottoController.checkLotto(lottoPaper, lottoAnswer);
        outputView.printResult(lottoResult);
        outputView.printRewardRate(lottoResult);
    }
}
