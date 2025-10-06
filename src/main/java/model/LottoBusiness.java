package model;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoBusiness {
    private final LottoNumberGenerator generator;

    public LottoBusiness() {
        this(new RandomLottoNumberGenerator());
    }

    public LottoBusiness(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public LottoNumbers createOneLotto() {
        return generator.generate();
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

    public Map<MatchResult, Integer> countMatchResults(List<LottoNumbers> allLotteries, List<LottoNumber> lastLotto) {
        Map<MatchResult, Integer> matchCounts = new EnumMap<>(MatchResult.class);
        for (MatchResult result : MatchResult.values()) {
            matchCounts.put(result, 0);
        }
        for (LottoNumbers oneLotto : allLotteries) {
            int count = matchLottoNumber(oneLotto.getNumbers(), lastLotto);
            MatchResult result = MatchResult.fromCount(count);
            matchCounts.put(result, matchCounts.get(result) + 1);
        }
        return matchCounts;
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

    public String calculateProfitRrate(Map<MatchResult, Integer> matchCounts, int money) {
        double profitRate = 0;
        for (MatchResult result : matchCounts.keySet()) {
            profitRate += matchCounts.get(result) * result.getReward();
        }
        profitRate = profitRate / (double) money;
        return String.format("%.2f", profitRate);
    }
}
