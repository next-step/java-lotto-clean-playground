package domain;

public enum Match {
    THREE(3, 5000) {
        @Override
        public String print() {
            return getCount() + "개 일치 (" + getPrice() + "원)- ";
        }

    },
    FOUR(4, 50000) {
        @Override
        public String print() {
            return getCount() + "개 일치 (" + getPrice() + "원)- ";
        }
    },
    FIVE(5,1500000) {
        @Override
        public String print() {
            return getCount() + "개 일치 (" + getPrice() + "원)- ";
        }
    },
    FIVEWITHBONUS(5,30000000) {
        @Override
        public String print() {
            return getCount() + "개 일치, 보너스 볼 일치(" + getPrice() + "원) - ";
        }
    },
    SIX(6, 2000000000) {
        @Override
        public String print() {
            return getCount() + "개 일치 (" + getPrice() + "원)- ";
        }
    };

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

    public abstract String print();

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
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Match{" +
                "price=" + price +
                ", count=" + count +
                '}';
    }
}
