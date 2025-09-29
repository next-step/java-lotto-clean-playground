package model;

import inputView.InputView;
import inputView.OutputView;
import util.LottoNumberGenerator;
import util.RandomLottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoBusiness {
    public LottoNumbers createOneLotto() {
        LottoNumberGenerator generator = new RandomLottoNumberGenerator();
        LottoNumbers oneLotto = generator.generate();
        return oneLotto;
    }

    public LottoNumbersRepository createLottos(int count) {
        LottoNumbersRepository repository = new LottoNumbersRepository();
        for (int i = 0; i < count; i++) {
            repository.addLottoNumbers(createOneLotto().sortNumbers());
        }
        return repository;
    }

    public LottoNumbers createLastLotto(String LastLotto) {
        List<LottoNumber> numbers = new ArrayList<>();
        String[] lastNumbers = LastLotto.split(",");
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
        return matchCount;
    }

    public String calculateProfitRrate(int[] matchCounts, int money) {
        double profitRate = (matchCounts[3] * 5000 + matchCounts[4] * 50000
                + matchCounts[5] * 150000 + matchCounts[6] * 2000000000) / (double) money;
        return String.format("%.2f", profitRate);
    }
}
