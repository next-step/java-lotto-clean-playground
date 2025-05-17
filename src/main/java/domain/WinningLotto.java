package domain;

public class WinningLotto {
    private final Numbers numbers;

    public WinningLotto(Numbers numbers) {
        this.numbers = numbers;
    }

    public int countMatch(Numbers other) {
        return (int) other.getNumbers().stream()
                .filter(numbers.getNumbers()::contains)
                .count();
    }

    public Numbers getNumbers() {
        return numbers;
    }
}
