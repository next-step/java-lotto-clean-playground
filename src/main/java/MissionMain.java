import domain.LottoSystem;
import domain.LottoWinningStatus;
import domain.WinningNumbers;
import generator.RandomLottoNumberGenerator;
import view.InputView;
import view.ResultView;

public class MissionMain {
    public static void main(String[] args) {
        final Integer inputMoney = InputView.inputMoney();
        LottoSystem lottoSystem = new LottoSystem(inputMoney, new RandomLottoNumberGenerator());
        ResultView.printLottoResult(lottoSystem.getPurchasedLottoNumbers());

        final var winningNumbers = new WinningNumbers(InputView.inputWinningNumbers());
        LottoWinningStatus lottoWinningStatus = new LottoWinningStatus(lottoSystem.getLottos(), winningNumbers);
        ResultView.printWinningStatistics(lottoWinningStatus.getLottoCountByMatchNumber(), lottoWinningStatus.getPrizeMoneyTable(), inputMoney);
        ResultView.printRateOfReturn(lottoWinningStatus.getPrizeRate(inputMoney));
    }
}
