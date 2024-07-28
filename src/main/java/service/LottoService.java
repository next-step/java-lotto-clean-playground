package service;

import domain.LottoAnswer;
import domain.LottoMachine;
import domain.LottoPrice;
import domain.LottoResult;
import domain.LottoTester;
import domain.RandomNumberGenerator;
import domain.Row;
import dto.LottoPaperDto;
import dto.LottoResultDto;
import java.util.List;

public class LottoService {
    public LottoPaperDto generatePaper(LottoPrice price, List<Row> manualRows) {
        LottoMachine lottoMachine = new LottoMachine(new RandomNumberGenerator());
        return new LottoPaperDto(lottoMachine.generatePaper(price, manualRows));
    }

    public LottoResultDto evaluatePaper(LottoPaperDto lottoPaperDto, LottoAnswer lottoAnswer) {
        LottoTester lottoTester = new LottoTester();
        LottoResult lottoResult = lottoTester.evaluatePaper(lottoPaperDto.toEntity(), lottoAnswer);
        return new LottoResultDto(lottoResult);
    }
}
