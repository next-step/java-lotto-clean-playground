package lotto.domain;

public class LottoException extends RuntimeException {
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
