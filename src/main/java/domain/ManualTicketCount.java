package domain;

public class ManualTicketCount {
    private final int count;

    public ManualTicketCount(int count, Price price) {
        validate(count, price);
        this.count = count;
    }

    private void validate(int count, Price price) {
        if (count < 0) {
            throw new IllegalArgumentException("개수가 음수일 수 없습니다.");
        }
        if (count > price.getBuyableLottoCount()) {
            throw new IllegalArgumentException("해당 개수만큼 생성하기에는 돈이 부족합니다.");
        }
    }

    public int getCount() {
        return count;
    }
}
