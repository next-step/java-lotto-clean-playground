package lotto.domain;

public record Payment(long value) {

  private static final int LOTTO_PRICE = 1000;

  public Payment {
    validatePayment(value);
  }

  private void validatePayment(long value) {
    if (value < LOTTO_PRICE) {
      throw new RuntimeException("로또 구매 금액은 최소 1000원 이상입니다.");
    }
  }
}
