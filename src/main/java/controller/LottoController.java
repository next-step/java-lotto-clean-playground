package controller;

import domain.Lottos;
import domain.NumberGenerator;
import service.LottoService;

public class LottoController {

    private final LottoService lottoService = new LottoService();

    public void execute(NumberGenerator numberGenerator) {
        Lottos lottos = lottoService.getLottos(numberGenerator);
        lottoService.printStatusOfLottos(lottos);
    }

}
