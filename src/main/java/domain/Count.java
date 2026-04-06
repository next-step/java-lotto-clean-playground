package domain;

public record Count(int count) {
    public Count {
        validate(count);
    }

    private void validate(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("개수가 음수일 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return String.valueOf(count);
    }
}
