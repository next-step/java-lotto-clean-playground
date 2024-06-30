package controller;

import dto.LottoPaperDto;
import dto.LottoResultDto;
import java.util.List;
import service.LottoService;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final LottoService lottoService = new LottoService();

    public LottoPaperDto buyLotto(int price){
        return lottoService.generatePaper(price);
    }

    public LottoResultDto checkLotto(LottoPaperDto lottoPaper, List<Integer> answer){
        LottoResultDto result = lottoService.evaluatePaper(lottoPaper, answer);
        return result;
    }
}
