package model;

import exception.CollectNumber;
import exception.BonusBall;

import java.util.List;

public class LottoStatistics {

    private final CollectNumber collectNumber;

    private RatingInfo ratingInfo;
    private BonusBall bonusBall;

    public LottoStatistics(final String collectText) {

        this.collectNumber = CollectNumber.from(collectText);
        ratingInfo = new RatingInfo();
    }

    public void configureMatchedCount(final List<Lotto> haveLottos) {

        for (Lotto lotto : haveLottos) {

            Rating rating = Rating.getRating(lotto.getCollectedCount(), lotto.isBonusCorrect());
            ratingInfo.update(rating);
        }
    }

    public double calculateRatetoReturn(final int inputMoney) {

        double sumMoney = 0;

        for (Rating rating : Rating.values()) {
            sumMoney += ratingInfo.getCount(rating) * rating.getReward();
        }

        return sumMoney / (double) inputMoney;
    }

    public void initBonusBall(final String numberText) {

        bonusBall = BonusBall.from(numberText, collectNumber);
    }

    public List<Integer> getCollectNumber() {
        return collectNumber.value();
    }

    public BonusBall getBonusBall() {
        return bonusBall;
    }

    public RatingInfo getRatingInfo() {
        return ratingInfo;
    }
}
