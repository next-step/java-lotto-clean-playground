import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoList;
import domain.RandomNumberGenerator;
import domain.WinningsCalculator;
import view.InputView;
import view.ResultView;

public class Application {

    public static void main(final String... args) {

        final int numberOfLotto = InputView.inputPurchasePrice();
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoList lottoList = new LottoList();

        int numberOfManualLotto = InputView.inputNumberOfManualLotto();
        int numberOfAutoLotto = numberOfLotto - numberOfManualLotto;

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < numberOfManualLotto; i++){
            String lottoNumber = InputView.inputManualLottoNumber();
            lottoList.addManualLotto(lottoGenerator.generateManualLotto(lottoNumber));
        }

        for (int i = 0; i < numberOfAutoLotto; i++) {
            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            lottoList.addAutoLotto(lottoGenerator.generateAutoLotto(randomNumberGenerator.getRandomNumber()));
        }
        ResultView.printLottoNumbers(lottoList);

        String input = InputView.inputWinningNumber();
        int bonusNumber = InputView.inputBonusNumber();
        WinningsCalculator winningsCalculator = new WinningsCalculator();
        Lotto winningNumber = lottoGenerator.generateManualLotto(input);
        winningsCalculator.updateWinningsResult(lottoList, winningNumber.getLottoNumber(), bonusNumber);
        ResultView.printWinningState(winningsCalculator.getWinningsResult(), winningsCalculator.getRateOfReturn());
    }
}
