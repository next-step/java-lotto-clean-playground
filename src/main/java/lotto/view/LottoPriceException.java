package lotto.view;

public abstract class LottoPriceException extends RuntimeException {
    public LottoPriceException(String message) {
        super(message);
    }

    public LottoPriceException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class Malformed extends LottoPriceException {
        public Malformed(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class Illegal extends LottoPriceException {
        public Illegal(String message) {
            super(message);
        }
    }
}
