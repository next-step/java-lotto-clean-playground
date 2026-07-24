package mission1;

import generator.LottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class FixedLottoNumberGenerator implements LottoNumberGenerator {
    private final List<Integer> fixedLottoNumbers;
    public FixedLottoNumberGenerator(List<Integer> fixedLottoNumbers) {
        this.fixedLottoNumbers = new ArrayList<>(fixedLottoNumbers);
    }

    @Override
    public List<Integer> generate() {
        return fixedLottoNumbers;
    }
}
