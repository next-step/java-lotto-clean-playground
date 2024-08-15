package service;

import domain.LottoAnswer;
import domain.LottoMachine;
import domain.LottoPrice;
import domain.LottoResult;
import domain.LottoTester;
import domain.RandomNumberGenerator;
import domain.Row;
import domain.LottoPaper;
import java.util.List;

public class LottoService {
    public LottoPaper generatePaper(LottoPrice price, List<Row> manualRows) {
        LottoMachine lottoMachine = new LottoMachine(new RandomNumberGenerator());
        return lottoMachine.generatePaper(price, manualRows);
    }

    public LottoResult evaluatePaper(LottoPaper lottoPaper, LottoAnswer lottoAnswer) {
        LottoTester lottoTester = new LottoTester();
        LottoResult lottoResult = lottoTester.evaluatePaper(lottoPaper, lottoAnswer);
        return lottoResult;
    }
}