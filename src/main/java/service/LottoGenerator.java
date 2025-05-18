package service;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private final LottoNumberGenerator numberGenerator;

    public LottoGenerator(LottoNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public Lottos generate(List<Lotto> manualLottos, int autoCount) {
        Lottos autoLottos = generateAutoLottos(autoCount);
        return Lottos.merge(new Lottos(manualLottos), autoLottos);
    }

    private Lottos generateAutoLottos(int autoCount) {
        List<Lotto> tickets = IntStream.range(0, autoCount)
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
