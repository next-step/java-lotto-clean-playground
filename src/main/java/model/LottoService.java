package model;


import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoService {
    private final LottoNumberGenerator generator;

    public LottoService() {
        this(new RandomLottoNumberGenerator());
    }

    public LottoService(LottoNumberGenerator generator) {
        this.generator = generator;
    }

    public LottoTicket createOneLotto() {
        return generator.generate();
    }

    public LottoTicketBundle createLottos(int count) {
        LottoTicketBundle repository = new LottoTicketBundle();
        for (int i = 0; i < count; i++) {
            repository.addLottoNumbers(createOneLotto().sortNumbers());
        }
        return repository;
    }

    public LottoTicket createInputLotto(String inputNumber) {
        List<LottoNumber> numbers = parseInputToNumbers(inputNumber);
        return new LottoTicket(numbers);
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

    public LottoTicketBundle mergeAutoAndManualLottos(LottoTicketBundle manual, LottoTicketBundle auto) {
        LottoTicketBundle repository = new LottoTicketBundle();
        for (LottoTicket lotto : manual.readLottoNumbersRepository()) {
            repository.addLottoNumbers(lotto);
        }

        for (LottoTicket lotto : auto.readLottoNumbersRepository()) {
            repository.addLottoNumbers(lotto);
        }
        return repository;
    }

    public Map<MatchResult, Integer> countMatchResults(
            List<LottoTicket> allLotteries,
            List<LottoNumber> winningLotto,
            LottoNumber bonusBall
    ) {
        Map<MatchResult, Integer> matchCounts = initializeMatchCounts();
        for (LottoTicket lotto : allLotteries) {
            int count = matchLottoNumber(lotto.getNumbers(), winningLotto);
            boolean bonusMatch = matchBonus(lotto.getNumbers(), bonusBall, count);
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

    private int matchLottoNumber(List<LottoNumber> lotto, List<LottoNumber> winningLotto) {
        return (int) lotto.stream()
                .filter(winningLotto::contains)
                .count();
    }

    private boolean matchBonus(List<LottoNumber> lotto, LottoNumber bonusBall, int count) {
        if (count == 5) {
            return lotto.contains(bonusBall);
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
