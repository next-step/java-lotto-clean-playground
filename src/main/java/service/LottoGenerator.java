package service;

import domain.Lotto;
import domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private final LottoNumberGenerator numberGenerator;

    public LottoGenerator(LottoNumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> generate(List<Lotto> manualLottos, int autoCount) {
        List<Lotto> autoLottos = generateAutoLottos(autoCount);
        List<Lotto> combined = new ArrayList<>(manualLottos);
        combined.addAll(autoLottos);
        return combined;
    }

    private List<Lotto> generateAutoLottos(int autoCount) {
        return IntStream.range(0, autoCount)
                .mapToObj(i -> toLotto(numberGenerator.generateLottoNumbers()))
                .collect(Collectors.toList());
    }

    private Lotto toLotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }
}
