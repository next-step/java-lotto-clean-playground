import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoNumber;
import domain.LottoNumbers;
import domain.LottoNumbersGenerator;
import domain.Lottos;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator();
        LottoGenerator lottoGenerator = new LottoGenerator(lottoNumbersGenerator);

        outputView.printPurchasePrice();
        final int lottoPurchasePrice = inputView.getLottoPurchasePrice();

        Lottos lottos = lottoGenerator.generate(lottoPurchasePrice);
        outputView.printPurchasedLottos(lottos);

        outputView.printWinningLotto();
        Lotto lotto = inputView.getWinningLottoNumbers();
        outputView.printBonusNumber();
        LottoNumber bonusNumber = new LottoNumber(inputView.getBonusNumber());
        WinningLotto winningLotto = new WinningLotto(lotto,bonusNumber);

        outputView.printLottoResult(lottos, winningLotto, lottoPurchasePrice);
    }
}
