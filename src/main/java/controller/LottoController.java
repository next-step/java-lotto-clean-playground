package controller;

import service.LottoService;

import java.util.List;

public class LottoController {
    private final LottoService lottoService;

    public LottoController() {
        this.lottoService = new LottoService();
    }

    public void run() {
        int gameTotalCount = getGameTotalCount();
        int manualLottoCount = getManualLottoCount();
        List<List<Integer>> manualLottoes = getManualLottoes(manualLottoCount);

        buyLottoes(gameTotalCount, manualLottoCount, manualLottoes);
        printPurchasedLottoes(manualLottoCount);

        List<Integer> winningLotto = getWinningLotto();
        int bonusNumber = getBonusNumber();

        processResults(winningLotto, bonusNumber);
    }

    private int getGameTotalCount() {
        return lottoService.getGameTotalCount(view.InputView.getMoney());
    }

    private int getManualLottoCount() {
        return view.InputView.getManualNumberCount();
    }

    private List<List<Integer>> getManualLottoes(int manualLottoCount) {
        return view.InputView.getManualLottoes(manualLottoCount);
    }

    private void buyLottoes(int gameTotalCount, int manualLottoCount, List<List<Integer>> manualLottoes) {
        lottoService.buyManualLottoes(manualLottoes);
        lottoService.buyAutoLottoes(gameTotalCount, manualLottoCount);
    }

    private void printPurchasedLottoes(int manualLottoCount) {
        List<List<Integer>> purchasedLottoes = lottoService.getPurchasedLottoes();
        view.ResultView.printUserLotto(purchasedLottoes, manualLottoCount);
    }

    private List<Integer> getWinningLotto() {
        return view.InputView.getWinningList();
    }

    private int getBonusNumber() {
        return view.InputView.getBonusNumber();
    }

    private void processResults(List<Integer> winningLotto, int bonusNumber) {
        List<List<Integer>> lottoResults = lottoService.getResults(winningLotto, bonusNumber);
        view.ResultView.printStatistics(lottoResults, lottoService.getPrizeRate());
    }

}
