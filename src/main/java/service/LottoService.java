package service;

import domain.LottoMachine;
import domain.LottoPrice;
import domain.LottoResult;
import domain.LottoTester;
import domain.RandomNumberGenerator;
import domain.Row;
import dto.LottoPaperDto;
import dto.LottoResultDto;

public class LottoService {
    public LottoPaperDto generatePaper(LottoPrice price) {
        LottoMachine lottoMachine = new LottoMachine(new RandomNumberGenerator());
        return lottoMachine.generatePaper(price).toDto();
    }

    public LottoResultDto evaluatePaper(LottoPaperDto lottoPaperDto, Row answer) {
        LottoTester lottoTester = new LottoTester();
        LottoResult lottoResult = lottoTester.evaluatePaper(lottoPaperDto.toEntity(), answer);
        return lottoResult.toDto();
    }
}
