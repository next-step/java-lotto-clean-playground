package domain.generator;

import domain.Lotto;
import domain.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {

    private final List<LottoNumber> manualLottoNumbers;

    public ManualLottoGenerator(List<Integer> inputManualNumbers) {
        this.manualLottoNumbers = inputManualNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }

    @Override
    public Lotto generate() {
        return new Lotto(manualLottoNumbers);
    }

    @Override
    public List<Lotto> generateLottos(int count) {
        throw new UnsupportedOperationException("수동 로또는 입력을 통해 생성됩니다.");
    }
}
