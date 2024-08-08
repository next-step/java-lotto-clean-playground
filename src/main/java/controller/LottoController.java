package controller;

import domain.BonusBall;
import domain.Lotto;
import domain.LottoPurchasePrice;
import domain.LottoResult;
import domain.Lottos;
import domain.ManualLottoCount;
import domain.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {

    private final static List<Rank> OUTPUT_ORDER_OF_RANK =
        List.of(Rank.FIFTH_PLACE, Rank.FOURTH_PLACE, Rank.THIRD_PLACE, Rank.SECOND_PLACE, Rank.FIRST_PLACE);

    private final OutputView outputView = new OutputView();
    private final InputView inputView = new InputView();
    private final LottoService lottoService = new LottoService();

    public void execute() {
        final LottoPurchasePrice lottoPurchasePrice = getPurchasePrice();
        final List<Lotto> manualLottos = getManualLottos(lottoPurchasePrice);
        final Lottos lottos = generateAndPrintLottos(lottoPurchasePrice, manualLottos);

        final Lotto winningLotto = getWinningLotto();
        final BonusBall bonusBall = getBonusBall(winningLotto);

        final LottoResult lottoResult = lottoService.getLottoResult(lottos, winningLotto, bonusBall);
        printLottoResultAndRoi(lottoResult, lottoPurchasePrice);
    }

    private LottoPurchasePrice getPurchasePrice() {
        outputView.printInputPurchasePriceGuide();
        final int userIntegerInput = inputView.getUserIntegerInput();
        return new LottoPurchasePrice(userIntegerInput);
    }

    private List<Lotto> getManualLottos(LottoPurchasePrice purchasePrice) {
        final ManualLottoCount manualLottoCount = getManualLottoCount(purchasePrice);

        outputView.printInputManualLottoNumber();

        List<Lotto> manualLottos = new ArrayList<>();
        for (int count = 0; count < manualLottoCount.getValue(); count++) {
            final Lotto manualLotto = getAndCreateLotto();
            manualLottos.add(manualLotto);
        }
        return manualLottos;
    }

    private ManualLottoCount getManualLottoCount(LottoPurchasePrice purchasePrice) {
        outputView.printInputManualLottoCount();
        final int userIntegerInput = inputView.getUserIntegerInput();
        return new ManualLottoCount(userIntegerInput, purchasePrice);
    }

    private Lotto getAndCreateLotto() {
        final List<Integer> lottoNumber = inputView.getLottoNumber();
        return Lotto.from(lottoNumber);
    }

    private Lottos generateAndPrintLottos(LottoPurchasePrice purchasePrice, List<Lotto> manualLottos) {
        final int totalLottoCount = purchasePrice.getLottoCount();
        outputView.printNumberOfLotto(manualLottos.size(), totalLottoCount);
        final Lottos lottos = lottoService.generateLottos(manualLottos, totalLottoCount);
        outputView.printStatusOfLottos(lottos.getStatus());
        return lottos;
    }

    private Lotto getWinningLotto() {
        outputView.printInputWinningNumbers();
        return getAndCreateLotto();
    }

    private BonusBall getBonusBall(Lotto winningLotto) {
        outputView.printInputBonusNumber();
        final int bonusNumber = inputView.getUserIntegerInput();
        return BonusBall.createIfNotInList(bonusNumber, winningLotto);
    }

    private void printLottoResultAndRoi(LottoResult lottoResult, LottoPurchasePrice lottoPurchasePrice) {
        outputView.printLottoResultStart();
        printLottoResult(lottoResult);
        outputView.printROI(lottoResult.getROI(lottoPurchasePrice));
    }

    private void printLottoResult(LottoResult lottoResult) {
        final Map<Rank, Integer> rankCountMap = lottoResult.getRankCountMap();
        for (Rank rank : OUTPUT_ORDER_OF_RANK) {
            outputView.printLottoResult(rank.getScoreCutoff(), rank.getPrizeMoney(), rank.isBonusBallMatching(),
                                        rankCountMap.get(rank));
        }
    }

}
