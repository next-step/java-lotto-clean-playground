package controller;

import inputView.InputView;
import inputView.OutputView;
import inputView.Price;
import model.LottoNumber;
import model.LottoNumbers;
import model.LottoNumbersRepository;
import util.LottoNumberGenerator;
import util.RandomLottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class AutoLottoControllerMethod {
    private final InputView inputView = new InputView();
    private final OutputView outView = new OutputView();

    public Price readPrice() {
        outView.printInputPrice();
        while (true) {
            Price price = readPriceException();
            if (price != null) {
                return price;
            }
        }
    }

    private Price readPriceException() {
        try {
            return new Price(Integer.parseInt(inputView.inputPrice()));
        } catch (NumberFormatException input) {
            outView.printInvalidNumber();
        } catch (IllegalArgumentException input) {
            outView.printInvalidPrice();
        }
        return null;
    }

    private LottoNumbers createOneLotto() {
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();
        LottoNumbers lottonumbers = generator.generate();
        return lottonumbers;
    }

    public LottoNumbersRepository createLottos(int count) {
        LottoNumbersRepository repository = new LottoNumbersRepository();
        for (int i = 0; i < count; i++) {
            repository.addLottoNumbers(createOneLotto().sortNumbers());
        }
        return repository;
    }

    public LottoNumbers createLastLotto() {
        outView.printInputLastLotto();
        List<LottoNumber> numbers = new ArrayList<>();
        String[] lastNumbers = inputView.inputLastLotto().split(",");
        for (int i = 0; i < lastNumbers.length; i++) {
            lastNumbers[i] = lastNumbers[i].trim();
            numbers.add(new LottoNumber(Integer.parseInt(lastNumbers[i])));
        }
        return new LottoNumbers(numbers);
    }

    public int[] countMatchResults(List<LottoNumbers> allLotteries, List<LottoNumber> lastLotto) {
        int[] matchCounts = {0, 0, 0, 0, 0, 0, 0};
        for (LottoNumbers oneLotto : allLotteries) {
            matchCounts[matchLottoNumber(oneLotto.getNumbers(), lastLotto)]++;
        }
        return matchCounts.clone();

    }

    private int matchLottoNumber(List<LottoNumber> oneLotto, List<LottoNumber> lastLotto) {
        List<Integer> lastNumbers = lastLotto.stream()
                .map(LottoNumber::getNumber)
                .toList();
        int matchCount = 0;
        for (LottoNumber number : oneLotto) {
            if (lastNumbers.contains(number.getNumber())) {
                matchCount++;
            }
        }
        return matchCount; //일치하는 번호 개수
    }
}
