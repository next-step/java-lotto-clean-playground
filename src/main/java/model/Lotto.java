package model;

public class Lotto {

    private final Numbers numbers;

    public Lotto(final Numbers numbers) {
        this.numbers = numbers;
    }

    public Numbers getNumbers() {
        return new Numbers(numbers.getNumbers());
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

}
