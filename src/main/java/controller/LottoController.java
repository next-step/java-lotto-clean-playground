package controller;

import domain.LottoPrice;
import domain.Row;
import dto.LottoPaperDto;
import dto.LottoResultDto;
import service.LottoService;


public class LottoController {
    private final LottoService lottoService = new LottoService();

    public LottoPaperDto buyLotto(LottoPrice price) {
        return lottoService.generatePaper(price);
    }

    public LottoResultDto checkLotto(LottoPaperDto lottoPaper, Row answer) {
        LottoResultDto result = lottoService.evaluatePaper(lottoPaper, answer);
        return result;
    }
}
