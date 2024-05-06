package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Lotto(List<LottoNumber> numbers) {
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final int LOTTO_PRICE = 1000;

    public static List<Lotto> purchaseLottos(int purchaseAmount) {
        int lottoCount = purchaseAmount / LOTTO_PRICE;
        return IntStream.range(0, lottoCount)
                .mapToObj(i -> generateLotto())
                .collect(Collectors.toList());
    }

    private static Lotto generateLotto() {
        List<LottoNumber> numbers = new ArrayList<>();
        while (numbers.size() < LOTTO_NUMBER_COUNT) {
            LottoNumber number = LottoNumber.generate();
            if (!numbers.contains(number)) {
                numbers.add(number);
            }
        }
        Collections.sort(numbers);
        return new Lotto(numbers);
    }

    public static LottoStatistics getStatistics(List<Lotto> lottos, List<LottoNumber> winningNumbers) {
        LottoStatistics statistics = new LottoStatistics();
        for (Lotto lotto : lottos) {
            int matchCount = getMatchCount(lotto, winningNumbers);
            statistics.addCount(matchCount);
        }
        return statistics;
    }

    private static int getMatchCount(Lotto lotto, List<LottoNumber> winningNumbers) {
        return (int) lotto.numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    @Override
    public String toString() {
        return numbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
