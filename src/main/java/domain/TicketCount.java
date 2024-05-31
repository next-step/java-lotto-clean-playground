package domain;

public class TicketCount {

    private static final int TICKET_PRICE = 1000;

    private final BuyMoney buyMoney;
    private final CountingTickets countingTickets;

    public TicketCount(BuyMoney buyMoney) {
        this.buyMoney = buyMoney;
        this.countingTickets = new CountingTickets(buyMoney);
    }

    public static class BuyMoney {
        private final int amount;
        private static final int EXCEPTION_INITIAL_NUMBER = 0;

        public BuyMoney(int amount, int count) {
            if (amount <= EXCEPTION_INITIAL_NUMBER) {
                throw new RuntimeException("로또를 구매할 돈은 음수가 될 수 없습니다.");
            }
            this.amount = amount - (TICKET_PRICE * count);
        }

        public int getAmount() {
            return amount;
        }
    }

    public static class CountingTickets {
        private final int count;

        public CountingTickets(BuyMoney buyMoney) {
            this.count = buyMoney.getAmount() / TICKET_PRICE;
        }

        public int getCount() {
            return count;
        }
    }
}
