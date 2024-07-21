package controller;

import domain.BonusNumBer;
import domain.LottoAnswer;
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

    public LottoResultDto checkLotto(LottoPaperDto lottoPaper, LottoAnswer lottoAnswer) {
        LottoResultDto result = lottoService.evaluatePaper(lottoPaper, lottoAnswer);
        return result;
    }
}
