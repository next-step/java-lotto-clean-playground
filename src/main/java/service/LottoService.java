package service;

import java.util.List;
import model.Lotto;
import model.LottoGenerator;
import model.Numbers;
import model.WinningLotto;
import utils.NumbersParser;

public class LottoService {

    public List<Lotto> createLotto(final int price) {
        return LottoGenerator.generateLotto(price);
    }

    public WinningLotto createWinningLotto(final String numbers) {
        // numbers -> List<Integer>로 변환(Parser)
        List<Integer> parsedNumbers = NumbersParser.parseIntegerList(numbers);
        // List<Integer> -> Nubmers 생성
        return new WinningLotto(new Numbers(parsedNumbers));
    }



}
