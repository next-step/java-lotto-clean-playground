package model;

public enum LottoRank {

    NO_PRIZE(0, 0) {
        @Override
        boolean isCorrectPrize(int equalCount, boolean bonusBallEqual) {
            return equalCount < THREE_EQUALS.equalCount;
        }
    },
    THREE_EQUALS(5_000, 3) {
        @Override
        boolean isCorrectPrize(int equalCount, boolean bonusBallEqual) {
            return equalCount == this.equalCount;
        }
    },
    FOUR_EQUALS(50_000, 4) {
        @Override
        boolean isCorrectPrize(int equalCount, boolean bonusBallEqual) {
            return equalCount == this.equalCount;
        }
    },
    FIVE_EQUALS(1_500_000, 5) {
        @Override
        boolean isCorrectPrize(int equalCount, boolean bonusBallEqual) {
            return equalCount == this.equalCount && !bonusBallEqual;
        }
    },
    FIVE_WITH_BONUS_EQUALS(30_000_000, 5) {
        @Override
        boolean isCorrectPrize(int equalCount, boolean bonusBallEqual) {
            return equalCount == this.equalCount && bonusBallEqual;
        }
    },
    SIX_EQUALS(2_000_000_000, 6) {
        @Override
        boolean isCorrectPrize(int equalCount, boolean bonusBallEqual) {
            return equalCount == this.equalCount;
        }
    };

    public final int prizeAmount;
    public final int equalCount;

    LottoRank(int prizeAmount, int equalCount) {
        this.prizeAmount = prizeAmount;
        this.equalCount = equalCount;
    }

    abstract boolean isCorrectPrize(int equalCount, boolean bonusBallEqual);

}