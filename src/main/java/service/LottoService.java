package service;

import domain.LottoMachine;
import domain.LottoResult;
import domain.LottoTester;
import domain.RandomNumberGenerator;
import dto.LottoPaperDto;
import dto.LottoResultDto;
import java.util.List;

public class LottoService {
    public LottoPaperDto generatePaper(int price){
        LottoMachine lottoMachine = new LottoMachine(new RandomNumberGenerator());
        lottoMachine.generatePaper(price);
        return lottoMachine.getWorkingPaper().toDto();
    }

    public LottoResultDto evaluatePaper(LottoPaperDto lottoPaperDto, List<Integer> answer){
        LottoTester lottoTester = new LottoTester();
        LottoResult lottoResult = lottoTester.evaluatePaper(lottoPaperDto.toEntity(), answer);
        return lottoResult.toDto();
    }
}
