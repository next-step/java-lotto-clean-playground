package service;

import domain.BonusNum;
import domain.LottoMachine;
import domain.LottoPrice;
import domain.LottoResult;
import domain.LottoTester;
import domain.RandomNumberGenerator;
import dto.LottoPaperDto;
import dto.LottoResultDto;
import dto.RowDto;

public class LottoService {
    public LottoPaperDto generatePaper(LottoPrice price) {
        LottoMachine lottoMachine = new LottoMachine(new RandomNumberGenerator());
        return new LottoPaperDto(lottoMachine.generatePaper(price));
    }

    public LottoResultDto evaluatePaper(LottoPaperDto lottoPaperDto, RowDto answer, BonusNum bonusNum) {
        LottoTester lottoTester = new LottoTester();
        LottoResult lottoResult = lottoTester.evaluatePaper(lottoPaperDto.toEntity(), answer.toEntity(), bonusNum);
        return new LottoResultDto(lottoResult);
    }
}
