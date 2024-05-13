package model;

import static model.Constants.*;

public enum RankType {
    FIFTH(CORRECT_NUM_THREE, FIFTH_PRIZE),
    FOURTH(CORRECT_NUM_FOUR, FOURTH_PRIZE),
    THIRD(CORRECT_NUM_FIVE, THIRD_PRIZE),
    SECOND(CORRECT_NUM_FIVE, SECOND_PRIZE),
    FIRST(CORRECT_NUM_SIX, FIRST_PRIZE);

    private int correctNum;
    private int prize;

    RankType(int correctNum, int prize) {
        this.correctNum = correctNum;
        this.prize = prize;
    }

    public static RankType getLottoRank(int correctNum, boolean hasBonusBall){
        if(correctNum  == CORRECT_NUM_THREE){
            return RankType.FIFTH;
        }
        else if(correctNum == CORRECT_NUM_FOUR){
            return RankType.FOURTH;
        }
        else if(correctNum  == CORRECT_NUM_FIVE){
            return isSecondRank(hasBonusBall);
        }
        else if(correctNum == CORRECT_NUM_SIX){
            return RankType.FIRST;
        }

        return null;
    }

    private static RankType isSecondRank(boolean hasBonusBall){
        if(hasBonusBall){
            return RankType.SECOND;
        }
        return RankType.THIRD;
    }

    public int getCorrectNum() {
        return correctNum;
    }

    public int getPrize() {
        return prize;
    }

}
