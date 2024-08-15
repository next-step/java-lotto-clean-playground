package controller;

import domain.LottoAnswer;
import domain.LottoPrice;
import domain.Row;
import domain.LottoPaper;
import domain.LottoResult;
import java.util.List;
import service.LottoService;


public class LottoController {
    private final LottoService lottoService = new LottoService();

    public LottoPaper buyLotto(LottoPrice price, List<Row> manualRows) {
        return lottoService.generatePaper(price, manualRows);
    }

    public LottoResult checkLotto(LottoPaper lottoPaper, LottoAnswer lottoAnswer) {
        LottoResult result = lottoService.evaluatePaper(lottoPaper, lottoAnswer);
        return result;
    }
}