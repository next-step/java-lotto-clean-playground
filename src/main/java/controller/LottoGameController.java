package controller;

import model.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

public class LottoGameController {
    private final LottoGenerator lottoGenerator = new LottoGenerator();
    private LottoPurchasePrice lottoPurchasePrice;
    private int lottoCount;
    private int manualLottoCount;
    private LottoList manualLottoList;
    private LottoList lottoList;
    private WinningLottoInfo winningLottoInfo;

    public void run() {
        inputLottoPurchasePrice();
        inputManualLottoCount();
        inputManualLottoList();
        makeLottoList(lottoCount);
        inputWinningLottoInfo();
        printLottoPrizeResult();
    }

    private void inputLottoPurchasePrice() {
        while (true) {
            try {
                lottoPurchasePrice = InputView.inputLottoPurchasePrice();
                lottoCount = lottoPurchasePrice.getLottoCount();
                break;
            } catch (Exception exception) {
                OutputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private void inputManualLottoCount() {
        while (true) {
            try {
                int manualLottoCount = InputView.inputManualLottoCount();
                if (manualLottoCount > lottoPurchasePrice.getLottoCount()) {
                    throw new IllegalArgumentException("수동 로또의 개수가 전체 구매할 수 있는 로또 수인 "
                            + lottoPurchasePrice.getLottoCount()
                            + "개보다 많아서는 안 됩니다. 수동 로또의 개수를 줄여보세요.");
                }
                this.manualLottoCount = manualLottoCount;
                break;
            } catch (Exception exception) {
                OutputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private void inputManualLottoList() {
        while (true) {
            try {
                manualLottoList = InputView.inputManualLottoList(manualLottoCount);
                break;
            } catch (Exception exception) {
                OutputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private void makeLottoList(int lottoCount) {
        int autoLottoCount = lottoCount - this.manualLottoList.size();
        List<Lotto> manualLottoList = this.manualLottoList.getValue();
        List<Lotto> autoLottoList = IntStream.range(0, autoLottoCount)
                .mapToObj(i -> lottoGenerator.generate())
                .collect(Collectors.toList());
        List<Lotto> concatedLottoList = new ArrayList<>();
        concatedLottoList.addAll(manualLottoList);
        concatedLottoList.addAll(autoLottoList);

        lottoList = new LottoList(concatedLottoList);
        OutputView.printLottoPurchases(lottoList, manualLottoCount, autoLottoCount);
    }

    private void inputWinningLottoInfo() {
        while (true) {
            try {
                final Lotto winningLotto = InputView.inputWinningLotto();
                final BonusNumber bonusNumber = InputView.inputBonusNumber();
                this.winningLottoInfo = new WinningLottoInfo(winningLotto, bonusNumber);
                break;
            } catch (Exception exception) {
                OutputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private void printLottoPrizeResult() {
        try {
            OutputView.printLottoPrizeResult(lottoList.getLottoResultByWinningLottoInfo(winningLottoInfo));
        } catch (Exception exception) {
            OutputView.printErrorMessage(exception.getMessage());
        }
    }
}
