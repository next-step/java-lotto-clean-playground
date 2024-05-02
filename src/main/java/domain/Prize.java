package domain;

public class Prize {

    private int firstPrizeQuantity;
    private int secondPrizeQuantity;
    private int thirdPrizeQuantity;
    private int fourthPrizeQuantity;
    // 이거 이렇게 각각 변수를 만드는게 가독성이 좋을까? 아니면 그냥 배열로 만드는게 가독성이 좋을까?
    // 배열로 만들지 않았을 때, 함수와 변수가 지나치게 많아지는 것 같다.

    private final int firstPrize = 2_000_000_000;
    private final int secondPrize = 1_500_000;
    private final int thirdPrize = 50_000;
    private final int fourthPrize = 5_000;

    public void addFirstPrizeQuantity() {
        firstPrizeQuantity++;
    }

    public void addSecondPrizeQuantity() {
        secondPrizeQuantity++;
    }

    public void addThirdPrizeQuantity() {
        thirdPrizeQuantity++;
    }

    public void addFourthPrizeQuantity() {
        fourthPrizeQuantity++;
    }

    public int getFirstPrizeQuantity() {
        return firstPrizeQuantity;
    }

    public int getSecondPrizeQuantity() {
        return secondPrizeQuantity;
    }

    public int getThirdPrizeQuantity() {
        return thirdPrizeQuantity;
    }

    public int getFourthPrizeQuantity() {
        return fourthPrizeQuantity;
    }

    public int getFirstPrize() {
        return firstPrize;
    }

    public int getSecondPrize() {
        return secondPrize;
    }

    public int getThirdPrize() {
        return thirdPrize;
    }

    public int getFourthPrize() {
        return fourthPrize;
    }

    public int getTotalPrize(){
        int totalPrize;
        totalPrize = firstPrize * firstPrizeQuantity + secondPrize * secondPrizeQuantity + thirdPrize * thirdPrizeQuantity + fourthPrize * fourthPrizeQuantity;
        return totalPrize;
    }
}
