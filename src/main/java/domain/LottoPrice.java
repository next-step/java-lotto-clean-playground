package domain;

public class LottoPrice {

  private static final int TICKET_PRICE = 1_000;
  private static final int MINIMUM_PURCHASE_MONEY = 1_000;

  private final long purchaseMoney;

  public LottoPrice(long purchaseMoney) {
    validatePurchaseMoney(purchaseMoney);
    this.purchaseMoney = purchaseMoney;
  }

  public long getPurchaseMoney() {
    return purchaseMoney;
  }

  private static void validatePurchaseMoney(long purchaseMoney) {
    if (purchaseMoney < MINIMUM_PURCHASE_MONEY) {
      throw new IllegalArgumentException(
          "구입 금액은 " + MINIMUM_PURCHASE_MONEY + "원 이상이어야 합니다."
      );
    }
  }

  public int calculateTicketCount() {
    return (int) (purchaseMoney / TICKET_PRICE);
  }
}
