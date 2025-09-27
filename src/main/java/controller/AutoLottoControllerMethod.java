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

    public void createLotteryStatistics(List<LottoNumbers> lottos, List<LottoNumber> lastLotto, int price) {
        int[] matchCount = {0, 0, 0, 0, 0, 0};
        for (LottoNumbers lotto : lottos) {
            matchCount[matchLottoNumber(lotto.getNumbers(), lastLotto)]++;
        }
        String three = String.valueOf(matchCount[2]);
        String four = String.valueOf(matchCount[3]);
        String five = String.valueOf(matchCount[4]);
        String six = String.valueOf(matchCount[5]);
        double prizeMoney = (matchCount[2] * 5000 + matchCount[3] * 50000
                + matchCount[4] * 150000 + matchCount[5] * 2000000000) / (double) price;
        String formatted = String.format("%.2f", prizeMoney);
        outView.printLotteryStatistics(three, four, five, six, formatted);
    }

    private int matchLottoNumber(List<LottoNumber> lotto, List<LottoNumber> lastLotto) {
        List<Integer> lastNumbers = lastLotto.stream()
                .map(LottoNumber::getNumber)
                .toList();
        int count = 0;
        for (LottoNumber number : lotto) {
            if (lastNumbers.contains(number.getNumber())) {
                count++;
            }
        }
        return count;
    }
}
