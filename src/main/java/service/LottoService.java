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

    public LottoPurchaseDto createLottoPurchaseRequest(InputView inputView, OutputView outputView) {
        int totalAmount = getTotalAmount(inputView, outputView);
        int manualLottoCount = getManualLottoCount(inputView, outputView, totalAmount);
        int autoLottoCount = calculateAutoLottoCount(totalAmount, manualLottoCount);

        List<Lotto> manualLottos = getManualLottos(inputView, outputView, manualLottoCount);
        Lottos manualLottosCollection = new Lottos(manualLottos);

        return new LottoPurchaseDto(totalAmount, manualLottoCount, manualLottosCollection, autoLottoCount);
    }

    public Lottos generateLottosFromRequest(LottoPurchaseDto purchaseRequest) {
        int totalLottoCount = purchaseRequest.totalAmount() / Lotto.PRICE;
        int manualLottoCount = purchaseRequest.manualLottoCount();
        int autoLottoCount = totalLottoCount - manualLottoCount;

        return lottoGenerator.generate(purchaseRequest.manualLottos().getLottos(), autoLottoCount);
    }

    private int getTotalAmount(InputView inputView, OutputView outputView) {
        outputView.printLottoPurchaseAmountPrompt();
        return LottoPurchaseAmountParser.parse(inputView.readLottoPurchaseAmount());
    }

    private int getManualLottoCount(InputView inputView, OutputView outputView, int totalAmount) {
        outputView.printManualLottoCountPrompt();
        return ManualLottoCountParser.parse(inputView.readManualLottoCount(), totalAmount);
    }

    private int calculateAutoLottoCount(int totalAmount, int manualLottoCount) {
        return (totalAmount / Lotto.PRICE) - manualLottoCount;
    }

    private List<Lotto> getManualLottos(InputView inputView, OutputView outputView, int manualLottoCount) {
        outputView.printManualPurchaseLottoNumbersPrompt();
        List<String> manualLottoNumbers = inputView.readManualLottoNumbers(manualLottoCount);

        return manualLottoNumbers.stream()
                .map(LottoNumbersInputParser::parse)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }
}
