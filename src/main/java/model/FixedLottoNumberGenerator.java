package model;

import java.util.List;

public class FixedLottoNumberGenerator implements LottoNumberGenerator {
    private final List<LottoNumber> fixedNumbers;

    public FixedLottoNumberGenerator(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 합니다.");
        }
        this.fixedNumbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    @Override
    public LottoTicket generate() {
        return new LottoTicket(fixedNumbers);
    }
}
