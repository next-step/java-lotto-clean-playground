import controller.LottoController;
import domain.BonusNum;
import domain.LottoPrice;
import dto.LottoPaperDto;
import dto.LottoResultDto;
import dto.RowDto;
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

        RowDto answer = inputView.getAnswer();
        BonusNum bonusNum = inputView.getBonus();
        LottoResultDto lottoResult = lottoController.checkLotto(lottoPaper, answer, bonusNum);
        outputView.printResult(lottoResult);
        outputView.printRewardRate(lottoResult);
    }
}
