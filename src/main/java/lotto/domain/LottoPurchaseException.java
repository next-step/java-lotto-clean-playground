package lotto.domain;

public abstract class LottoPurchaseException extends RuntimeException {
    public LottoPurchaseException(String message) {
        super(message);
    }

    public LottoPurchaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public static class IllegalCount extends LottoPurchaseException {
        public IllegalCount(String message) {
            super(message);
        }
    }
}
