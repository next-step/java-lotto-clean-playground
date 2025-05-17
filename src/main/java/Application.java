import domain.Lotto;
import domain.Numbers;
import domain.WinningLotto;
import domain.generator.NumberGenerator;
import domain.generator.RandomNumberGenerator;
import view.InputView;
import view.ResultView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        NumberGenerator numberGenerator = new RandomNumberGenerator();

        int amount = inputView.readAmount();
        int count = amount / 1000;
        resultView.printLottoCount(count);
        for (int i = 0; i < count; i++) {
            Lotto lotto = new Lotto(numberGenerator);
            resultView.printLottoNumbers(lotto);
        }

        Numbers winningNumbers = new Numbers(inputView.readWinningNumbers());
        WinningLotto winningLotto = new WinningLotto(winningNumbers);
    }
}
