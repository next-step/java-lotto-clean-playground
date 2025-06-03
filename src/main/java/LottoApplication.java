import domain.Lotto;
import domain.LottoGenerator;
import domain.LottoNumber;
import domain.LottoNumbers;
import domain.LottoNumbersGenerator;
import domain.LottoPurchasePrice;
import domain.LottoResult;
import domain.Lottos;
import domain.ManualLottoCount;
import domain.WinningLotto;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        final InputView inputView = new InputView();
        final OutputView outputView = new OutputView();
        final LottoNumbersGenerator lottoNumbersGenerator = new LottoNumbersGenerator();
        final LottoGenerator lottoGenerator = new LottoGenerator(lottoNumbersGenerator);

        final LottoPurchasePrice lottoPurchasePrice = getLottoPurchasePrice(outputView, inputView);
        final ManualLottoCount manualLottoCount = getManualLottoCount(outputView, inputView);
        final Lottos manualLottos = generateManualLottos(outputView, inputView, manualLottoCount.getCount());
        final Lottos autoLottos = lottoGenerator.generate(lottoPurchasePrice.getPurchasePrice(),
                manualLottoCount.getCount());
        final Lottos totalLottos = mergeManualLottosAndAutoLottos(manualLottos, autoLottos);
        outputView.printPurchasedLottos(totalLottos);

        final WinningLotto winningLotto = generateWinningLotto(outputView, inputView);
        LottoResult lottoResult = LottoResult.createResult(totalLottos, winningLotto,
                lottoPurchasePrice.getPurchasePrice());
        outputView.printLottoResult(lottoResult);
    }

    private static ManualLottoCount getManualLottoCount(OutputView outputView, InputView inputView) {
        outputView.printManualLottoCountInputMessage();
        return new ManualLottoCount(inputView.getManualLottoCount());
    }

    private static LottoPurchasePrice getLottoPurchasePrice(OutputView outputView, InputView inputView) {
        outputView.printPurchasePriceInputMessage();
        return new LottoPurchasePrice(inputView.getLottoPurchasePrice());
    }

    private static WinningLotto generateWinningLotto(OutputView outputView, InputView inputView) {
        outputView.printWinningLottoInputMessage();
        List<Integer> numbers = inputView.getWinningLottoNumbers();
        Lotto lotto = new Lotto(new LottoNumbers(numbers.stream()
                                                    .map(LottoNumber::of)
                                                    .toList()));
        outputView.printBonusNumberInputMessage();
        LottoNumber bonusNumber = LottoNumber.of(inputView.getBonusNumber());
        return new WinningLotto(lotto, bonusNumber);
    }

    private static Lottos mergeManualLottosAndAutoLottos(Lottos manualLottos, Lottos autoLottos) {
        List<Lotto> allLottoList = new ArrayList<>();
        allLottoList.addAll(manualLottos.lottos());
        allLottoList.addAll(autoLottos.lottos());
        return new Lottos(allLottoList);
    }

    private static Lottos generateManualLottos(OutputView outputView, InputView inputView, int manualLottoCount) {
        if (manualLottoCount == 0) {
            return new Lottos(List.of());
        }
        outputView.printManualLottosInputMessage();
        List<List<Integer>> manualLottoNumbers = inputView.getManualLottos(manualLottoCount);
        return new Lottos(
                manualLottoNumbers.stream()
                        .map(numbers -> numbers.stream()
                                .map(LottoNumber::of)
                                .toList())
                        .map(LottoNumbers::new)
                        .map(Lotto::new)
                        .toList()
        );
    }
}
