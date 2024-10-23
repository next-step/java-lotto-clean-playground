package domain;

import java.util.Arrays;

public enum Match {
    NONE(0, 0, false),
    THREE(3, 5000, false),
    FOUR(4, 50000, false),
    FIVE(5,1500000, false),
    FIVEWITHBONUS(5,30000000, true),
    SIX(6, 2000000000,false);
    private final int price;
    private final int count;
    private final boolean bonus;


    Match(int count, int price, boolean bonus) {
        this.count = count;
        this.price = price;
        this.bonus = bonus;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public static Match from(int c, boolean bonus) {
        return Arrays.stream(Match.values())
                .filter(m -> m.getCount() == c)
                .filter(m -> m.bonus == bonus)
                .findFirst()
                .orElse(NONE);
    }
}
