package domain;

public class Lotto {
    public static final int PRICE = 1000;

    private final LottoNumbers numbers;

    public Lotto(LottoNumbers numbers) {
        this.numbers = numbers;
    }

    public LottoNumbers getNumbers() {
        return numbers;
    }
}

