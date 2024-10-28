package lotto.model;

public enum TicketPrize {
    THREE_MATCH(3, 5000),
    FOUR_MATCH(4, 50000),
    FIVE_MATCH(5, 1500000),
    FIVE_MATCH_WITH_BONUS(TicketPrize.BONUSBALL_KEY, 30000000),
    SIX_MATCH(6, 2000000000);

    private final static int BONUSBALL_KEY = 7;

    private final int matchNum;
    private final int prize;

    TicketPrize(int matchNum, int prize) {
        this.matchNum = matchNum;
        this.prize= prize;
    }

    public int getPrize(int matchNum) {
        return prize;
    }

    public static int decisionPrice(int matchNum) {
        for (TicketPrize ticketPrize : TicketPrize.values()) {
            if (matchNum == ticketPrize.matchNum) {
                return ticketPrize.getPrize(matchNum);
            }
        }
        return 0;
    }

}
