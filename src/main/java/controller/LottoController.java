package controller;

import model.Lotto;
import model.Numbers;
import model.WinningLotto;
import model.WinningStat;
import service.LottoService;
import view.InputView;
import view.ResultView;

import java.util.List;

public class LottoController {

    private final LottoService service;

    public LottoController(final LottoService service) {
        this.service = service;
    }

    public void lottoStart(){
        List<Lotto> lottos = createLotto();
        WinningStat stat = calculateLottos(lottos);
        ResultView.printLottoResult(stat);
    }

    private List<Lotto> createLotto() {
        int price = InputView.getPrice();
        List<Lotto> lottos = service.createLotto(price);
        ResultView.printLottos(lottos);
        return lottos;
    }

    private WinningStat calculateLottos(List<Lotto> lottos) {
        WinningLotto winningLotto = createWinningLotto();
        WinningStat stat = new WinningStat();
        winningLotto.getLottoResult(lottos, stat);
        return stat;
    }

    private WinningLotto createWinningLotto() {
        String numbers = InputView.getWinningNumbers();
        return service.createWinningLotto(numbers);
    }

}
