package controller;

import domain.BonusBall;
import domain.Lotto;
import domain.LottoResult;
import domain.Lottos;
import domain.LottoPurchasePrice;
import domain.ManualLottoCount;
import domain.Rank;
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

    public void execute() { // TODO : 길이 줄이기
        final LottoPurchasePrice lottoPurchasePrice = getPurchasePrice();
        final ManualLottoCount manualLottoCount = getManualLottoCount(lottoPurchasePrice);

        final Lottos lottos = lottoService.generateLottos();
        printLottosStatus(lottos);

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

    private ManualLottoCount getManualLottoCount(LottoPurchasePrice purchasePrice) {
        outputView.printInputManualLottoCount();
        final int userIntegerInput = inputView.getUserIntegerInput();
        return new ManualLottoCount(userIntegerInput, purchasePrice);
    }

    private void printLottosStatus(Lottos lottos) {
        outputView.printNumberOfLotto(lottos.getSize());
        outputView.printStatusOfLottos(lottos.getStatus());
    }

    private Lotto getWinningLotto() {
        outputView.printInputWinningNumbers();
        final List<Integer> winningNumbers = inputView.getWinningNumbers();
        return Lotto.from(winningNumbers);
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
