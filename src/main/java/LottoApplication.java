import domain.LottoGenerator;
import domain.LottoNumbersGenerator;
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

        outputView.printPurchasedLottos(lottoGenerator.generate(lottoPurchasePrice));
    }
}
