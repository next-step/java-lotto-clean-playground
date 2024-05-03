package controller;

import model.LottoGenerator;
import model.Lotto;
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
        int price = InputView.getPrice();
        List<Lotto> lottos = service.lottoCreate(price);
        ResultView.printLottos(lottos);
    }

}
