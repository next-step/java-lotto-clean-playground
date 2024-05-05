package controller;

import model.Lotto;
import model.WinningLotto;
import model.LottoStat;
import service.LottoService;
import view.InputReader;
import view.ResultPrinter;

import java.util.List;

public class LottoController {

    private final LottoService service;

    public LottoController(final LottoService service) {
        this.service = service;
    }

    public void lottoAutoStart(){
        int inputPrice = inputPrice();
        List<Lotto> lottos = createAutoLottos(inputPrice);
        showLottoResult(lottos, inputPrice);
    }

    private int inputPrice() {
        return InputReader.getPrice();
    }

    private List<Lotto> createAutoLottos(final int price) {
        final int manualLottoCount = 0;
        List<Lotto> lottos = service.createAutoLotto(price);
        ResultPrinter.printLottos(lottos, manualLottoCount);
        return lottos;
    }

    private void showLottoResult(final List<Lotto> lottos, final int inputPrice) {
        LottoStat stat = calculateLottos(lottos);
        ResultPrinter.printLottoResult(stat);
        double totalReturnRate = stat.getTotalReturnRate(inputPrice);
        ResultPrinter.printTotalReturnRate(totalReturnRate);
    }

    private LottoStat calculateLottos(final List<Lotto> lottos) {
        WinningLotto winningLotto = createWinningLotto();
        LottoStat stat = new LottoStat();
        winningLotto.getLottoResult(lottos, stat);
        return stat;
    }

    private WinningLotto createWinningLotto() {
        String numbers = InputReader.getWinningNumbers();
        int bonusNumber = InputReader.getBonusNumber();
        return service.createWinningLotto(numbers, bonusNumber);
    }

    public void lottoManualStart() {
        int inputPrice = inputPrice();
        int manualLottoCount = InputReader.getManualLottoCount();
        List<String> manualValues = InputReader.getManualLottoNumbers(manualLottoCount);
        List<Lotto> lottos = createManualLottos(inputPrice, manualValues);
        showLottoResult(lottos, inputPrice);
    }

    private List<Lotto> createManualLottos(final int price, final List<String> manualValues) {
        List<Lotto> lottos = service.createManualLotto(price,manualValues);
        ResultPrinter.printLottos(lottos, manualValues.size());
        return lottos;
    }

}
