import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoNumber;
import domain.LottoNumbers;
import domain.LottoNumbersGenerator;
import domain.Lottos;
import domain.WinningLotto;
import java.util.ArrayList;
import java.util.List;
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

        outputView.printManualLottoCount();
        int manualLottoCount = inputView.getManualLottoCount();

        outputView.printManualLottos();
        List<List<Integer>> manualLottoNumbers = inputView.getManualLottos(manualLottoCount);
        Lottos manualLottos = new Lottos(
                manualLottoNumbers.stream()
                        .map(numbers -> numbers.stream()
                                .map(LottoNumber::new)
                                .toList())
                        .map(LottoNumbers::new)
                        .map(Lotto::new)
                        .toList()
        );

        Lottos autoLottos = lottoGenerator.generate(lottoPurchasePrice,manualLottoCount);

        List<Lotto> allLottoList = new ArrayList<>();
        allLottoList.addAll(manualLottos.getLottos());
        allLottoList.addAll(autoLottos.getLottos());
        Lottos totalLottos = new Lottos(allLottoList);
        outputView.printPurchasedLottos(totalLottos);

        outputView.printWinningLotto();
        Lotto lotto = inputView.getWinningLottoNumbers();
        outputView.printBonusNumber();
        LottoNumber bonusNumber = new LottoNumber(inputView.getBonusNumber());
        WinningLotto winningLotto = new WinningLotto(lotto,bonusNumber);

        outputView.printLottoResult(totalLottos, winningLotto, lottoPurchasePrice);
    }
}
