package controller;

import domain.LottoAnswer;
import domain.LottoPrice;
import domain.Row;
import dto.LottoPaperDto;
import dto.LottoResultDto;
import java.util.List;
import service.LottoService;


public class LottoController {
    private final LottoService lottoService = new LottoService();

    public LottoPaperDto buyLotto(LottoPrice price, List<Row> manualRows) {
        return lottoService.generatePaper(price, manualRows);
    }

    public LottoResultDto checkLotto(LottoPaperDto lottoPaper, LottoAnswer lottoAnswer) {
        LottoResultDto result = lottoService.evaluatePaper(lottoPaper, lottoAnswer);
        return result;
    }
}
