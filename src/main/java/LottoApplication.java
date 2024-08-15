import controller.LottoController;
import domain.LottoAnswer;
import domain.LottoPrice;
import domain.Row;
import domain.LottoPaper;
import domain.LottoResult;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        final LottoController lottoController = new LottoController();
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        LottoPrice price = new LottoPrice(inputView.getPrice(), inputView.getManualNumber());
        List<Row> manualRows = inputView.getManualRows(price.manualNumber());
        LottoPaper lottoPaper = lottoController.buyLotto(price, manualRows);
        outputView.printRowNumber(price.manualNumber(), lottoPaper.getRowNum());
        outputView.printPaper(lottoPaper);

        LottoAnswer lottoAnswer = new LottoAnswer(inputView.getAnswer(), inputView.getBonus());
        LottoResult lottoResult = lottoController.checkLotto(lottoPaper, lottoAnswer);
        outputView.printResult(lottoResult);
        outputView.printRewardRate(lottoResult);
    }
}
