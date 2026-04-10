package domain;

public record BonusBall(LottoNumber number) {
    public BonusBall {
        validate(number);
    }

    private void validate(LottoNumber number) {
        if (number == null) {
            throw new IllegalArgumentException("보너스 볼은 필수입니다.");
        }
    }
}
