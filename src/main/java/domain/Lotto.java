package domain;

public class Lotto {
    private final LottoNumbers numbers;

    public Lotto(LottoNumbers numbers) {
        this.numbers = numbers;
    }

    public LottoNumbers getNumbers() {
        return numbers;
    }
}
