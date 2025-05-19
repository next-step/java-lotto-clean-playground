package service;

import domain.Lotto;
import domain.Lottos;
import dto.LottoPurchaseDto;
import utils.LottoNumbersInputParser;
import utils.LottoPurchaseAmountParser;
import utils.ManualLottoCountParser;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoService {

    private final LottoGenerator lottoGenerator;

    public LottoService(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    public LottoPurchaseDto preparePurchase(InputView inputView, OutputView outputView) {
        int totalAmount = readPurchaseAmount(inputView, outputView);
        int manualLottoCount = readManualCount(inputView, outputView, totalAmount);
        int autoLottoCount = calculateAutoCount(totalAmount, manualLottoCount);

        List<Lotto> manualLottos = readManualLottos(inputView, outputView, manualLottoCount);
        Lottos manualLottosCollection = new Lottos(manualLottos);

        return new LottoPurchaseDto(totalAmount, manualLottoCount, manualLottosCollection, autoLottoCount);
    }

    public Lottos generateLottos(LottoPurchaseDto purchaseRequest) {
        int totalLottoCount = purchaseRequest.totalAmount() / Lotto.PRICE;
        int manualLottoCount = purchaseRequest.manualLottoCount();
        int autoLottoCount = totalLottoCount - manualLottoCount;

        return lottoGenerator.generate(purchaseRequest.manualLottos().getLottos(), autoLottoCount);
    }

    private int readPurchaseAmount(InputView inputView, OutputView outputView) {
        outputView.printLottoPurchaseAmountPrompt();
        return LottoPurchaseAmountParser.parse(inputView.readLottoPurchaseAmount());
    }

    private int readManualCount(InputView inputView, OutputView outputView, int totalAmount) {
        outputView.printManualLottoCountPrompt();
        return ManualLottoCountParser.parse(inputView.readManualLottoCount(), totalAmount);
    }

    private int calculateAutoCount(int totalAmount, int manualLottoCount) {
        return (totalAmount / Lotto.PRICE) - manualLottoCount;
    }

    private List<Lotto> readManualLottos(InputView inputView, OutputView outputView, int manualLottoCount) {
        outputView.printManualPurchaseLottoNumbersPrompt();
        List<String> manualLottoNumbers = inputView.readManualLottoNumbers(manualLottoCount);

        return manualLottoNumbers.stream()
                .map(LottoNumbersInputParser::parse)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }
}
