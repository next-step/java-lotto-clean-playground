package lotto.view;

public sealed class LottoPriceException extends RuntimeException {
    public LottoPriceException(String message) {
        super(message);
    }

    public LottoPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public static final class Malformed extends LottoPriceException {
        public Malformed(String message) {
            super(message);
        }

        public Malformed(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static final class Illegal extends LottoPriceException {
        public Illegal(String message) {
            super(message);
        }
    }
}
