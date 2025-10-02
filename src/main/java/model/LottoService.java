package model;

import java.util.*;

public class LottoService {
    private final LottoNumberGenerator generator;

    public LottoService() {
        this(new RandomLottoNumberGenerator());
    }

    public LottoService(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public LottoNumbers createOneLotto() {
        return generator.generate();
    }

    public LottoTicketBundle createLottos(int count) {
        LottoTicketBundle repository = new LottoTicketBundle();
        for (int i = 0; i < count; i++) {
            repository.addLottoNumbers(createOneLotto().sortNumbers());
        }
        return repository;
    }

    public LottoNumbers createInputLotto(String inputNumber) {
        List<LottoNumber> numbers = parseInputToNumbers(inputNumber);
        return new LottoNumbers(numbers);
    }

    private List<LottoNumber> parseInputToNumbers(String inputNumber) {
        String[] rawNumbers = inputNumber.split(",");
        List<LottoNumber> numbers = new ArrayList<>();
        for (String raw : rawNumbers) {
            numbers.add(new LottoNumber(Integer.parseInt(raw.trim())));
        }
        return numbers;
    }

    public LottoTicketBundle createManualLottos(List<String> inputs) {
        LottoTicketBundle repository = new LottoTicketBundle();
        for (String input : inputs) {
            repository.addLottoNumbers(createInputLotto(input));
        }
        return repository;
    }

    public LottoTicketBundle mergeRepositories(LottoTicketBundle manual, LottoTicketBundle auto) {
        LottoTicketBundle repository = new LottoTicketBundle();
        for (LottoNumbers lotto : manual.readLottoNumbersRepository()) {
            repository.addLottoNumbers(lotto);
        }

        for (LottoNumbers lotto : auto.readLottoNumbersRepository()) {
            repository.addLottoNumbers(lotto);
        }
        return repository;
    }

    public Map<MatchResult, Integer> countMatchResults(List<LottoNumbers> allLotteries,
                                                       List<LottoNumber> lastLotto,
                                                       LottoNumber bonusBall) {
        Map<MatchResult, Integer> matchCounts = initializeMatchCounts();
        for (LottoNumbers oneLotto : allLotteries) {
            int count = matchLottoNumber(oneLotto.getNumbers(), lastLotto);
            boolean bonusMatch = matchBonus(oneLotto.getNumbers(), bonusBall, count);
            MatchResult result = MatchResult.fromCount(count, bonusMatch);
            matchCounts.put(result, matchCounts.get(result) + 1);
        }
        return matchCounts;
    }

    private Map<MatchResult, Integer> initializeMatchCounts() {
        Map<MatchResult, Integer> counts = new EnumMap<>(MatchResult.class);
        for (MatchResult result : MatchResult.values()) {
            counts.put(result, 0);
        }
        return counts;
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

    private boolean matchBonus(List<LottoNumber> oneLotto, LottoNumber bonusBall, int count) {
        if (count == 5) {
            return oneLotto.contains(bonusBall);
        }
        return false;
    }

    public String calculateProfitRate(Map<MatchResult, Integer> matchCounts, int money) {
        double profitRate = 0;
        for (MatchResult result : matchCounts.keySet()) {
            profitRate += matchCounts.get(result) * result.getReward();
        }
        profitRate = profitRate / (double) money;
        return String.format("%.2f", profitRate);
    }
}
