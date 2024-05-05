package service;

import java.util.List;
import model.Lotto;
import model.LottoGenerator;
import model.Numbers;
import model.WinningLotto;
import utils.NumbersParser;

public class LottoService {

    public List<Lotto> createAutoLotto(final int price) {
        return LottoGenerator.createAutoLottos(price);
    }

    public List<Lotto> createManualLotto(final int price, final List<String> manualValues) {
        List<Numbers> manualNumbers = manualValues.stream()
                .map(NumbersParser::parseIntegerList)
                .map(Numbers::new)
                .toList();
        return LottoGenerator.createManualLottos(price, manualNumbers);
    }

    public WinningLotto createWinningLotto(final String numbers, final int bonusNumber) {
        List<Integer> parsedNumbers = NumbersParser.parseIntegerList(numbers);
        return new WinningLotto(new Numbers(parsedNumbers), bonusNumber);
    }

}
