package domain;


import java.util.List;

public class LottoNumbers {
    public static final int MIN = 1;
    public static final int MAX = 45;
    public static final int SIZE = 6;

    private final List<Integer> values;

    public LottoNumbers(List<Integer> values) {
        validate(values);
        this.values = List.copyOf(values);
    }

    public boolean contains(int n) {
        return values.contains(n);
    }

    public List<Integer> asList() {
        return values;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != SIZE) throw new IllegalArgumentException("번호는 6개여야 합니다.");
        if (numbers.stream().distinct().count() != SIZE) throw new IllegalArgumentException("번호는 중복될 수 없습니다.");
        if (numbers.stream().anyMatch(n -> n < MIN || n > MAX)) throw new IllegalArgumentException("번호는 1~45 범위여야 합니다.");
    }
}
