package lotto;

import java.util.HashSet;
import java.util.List;

public record Lotto(List<LottoNumber> numbers) {
    public Lotto {
        if (numbers.size() != 6) {
            throw new LottoException.WrongNumberCount();
        }

        checkDuplicateNumbers(numbers);
    }

    private void checkDuplicateNumbers(List<LottoNumber> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new LottoException.DuplicateNumber();
        }
    }

    public void printToConsole() {
        List<String> numberString = numbers.stream()
                .map(LottoNumber::toString)
                .toList();
        System.out.println("[" + String.join(", ", numberString) + "]");
    }

    public static class LottoException extends RuntimeException {
        public LottoException(String message) {
            super(message);
        }

        public static class WrongNumberCount extends LottoException {
            public WrongNumberCount() {
                super("숫자의 갯수가 잘못되었습니다.");
            }
        }

        public static class DuplicateNumber extends LottoException {
            public DuplicateNumber() {
                super("중복된 숫자가 있습니다.");
            }
        }
    }
}
