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

    public void lottoStart(){
        int inputPrice = inputPrice();
        List<Lotto> lottos = createLotto(inputPrice);
        LottoStat stat = calculateLottos(lottos);
        ResultPrinter.printLottoResult(stat);
        double totalReturnRate = stat.getTotalReturnRate(inputPrice);
        ResultPrinter.printTotalReturnRate(totalReturnRate);
    }

    private int inputPrice() {
        return InputReader.getPrice();
    }

    private List<Lotto> createLotto(int price) {
        List<Lotto> lottos = service.createLotto(price);
        ResultPrinter.printLottos(lottos);
        return lottos;
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

}
