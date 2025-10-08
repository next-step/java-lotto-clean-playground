package domain.value;

public record ManualCount(int value) {
    public ManualCount {
        if (value < 0) throw new IllegalArgumentException("수동 로또티켓의 개수는 음수일 수 없습니다.");
    }

    public void validateAgainstTotal(int total) {
        if (value > total) throw new IllegalArgumentException("수동 로또티켓의 개수가 전체 로또티켓의 갯수를 초과했습니다.");
    }
}
