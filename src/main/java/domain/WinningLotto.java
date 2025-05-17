package domain;

public class WinningLotto {
    private final Numbers numbers;

    public WinningLotto(Numbers numbers) {
        this.numbers = numbers;
    }

    public Numbers getNumbers() {
        return numbers;
    }
}
