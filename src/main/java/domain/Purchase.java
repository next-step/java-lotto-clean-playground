package domain;

public class Purchase {
    private final int price;

    private Purchase(int price) {
        this.price = price;
    }

    private int getLottoCount() {
        return price / 1000;
    }

    private int getPrice() {
        return price;
    }
}
