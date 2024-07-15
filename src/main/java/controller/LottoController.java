package controller;

import domain.BonusNum;
import domain.LottoPrice;
import dto.LottoPaperDto;
import dto.LottoResultDto;
import dto.RowDto;
import service.LottoService;


public class LottoController {
    private final LottoService lottoService = new LottoService();

    public LottoPaperDto buyLotto(LottoPrice price) {
        return lottoService.generatePaper(price);
    }

    public LottoResultDto checkLotto(LottoPaperDto lottoPaper, RowDto answer, BonusNum bonusNum) {
        LottoResultDto result = lottoService.evaluatePaper(lottoPaper, answer, bonusNum);
        return result;
    }
}
