package domain;

public enum Match {
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE(5,1500000),
    FIVEWITHBONUS(5,30000000),
    SIX(6, 2000000000);
    private final int price;
    private final int count;


    Match(int count, int price) {
        this.count = count;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public static Match from(int c, boolean bonus) {
        if (c == THREE.count) {
            return THREE;
        } else if (c == FOUR.count) {
            return FOUR;
        } else if (c == FIVE.count && !bonus) {
            return FIVE;
        } else if (c == FIVE.count && bonus) {
            return FIVEWITHBONUS;
        } else if (c == SIX.count) {
            return SIX;
        }

        return null;
    }
}
