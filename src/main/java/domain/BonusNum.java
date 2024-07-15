package domain;

public record BonusNum(int num) {

    public BonusNum {
        rangeCheck(num);
    }

    private void rangeCheck(int num) {
        if (num < 1 || num > 45) {
            throw new IllegalArgumentException("보너스 번호는 1에서 45 사이 입니다.");
        }
    }

}
