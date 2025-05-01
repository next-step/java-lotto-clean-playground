package domain.generator;

import domain.Lotto;
import domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {

    private final List<String> manualInputs;

    public ManualLottoGenerator(List<String> manualInputs) {
        this.manualInputs = manualInputs;
    }

    @Override
    public List<Lotto> generateLottoList(int count) {
        return manualInputs.stream()
                .map(this::parse)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }

    private List<LottoNumber> parse(String numbers) {
        return Arrays.stream(numbers.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
