package service;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private final NumberGenerator numberGenerator;

    public LottoGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos generate(int count) {
        return generateByCount(count);
    }

    private Lottos generateByCount(int count) {
        List<Lotto> tickets = IntStream.range(0, count)
                .mapToObj(i -> toLotto(numberGenerator.generate()))
                .collect(Collectors.toList());

        return new Lottos(tickets);
    }

    private Lotto toLotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }
}
