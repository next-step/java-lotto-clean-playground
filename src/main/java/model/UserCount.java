package model;

public class UserCount {

    private static final int LOTTO_UNIT = 1000;

    private final int count;
    private final int price;

    public UserCount(String price){
        int priceNum = convertStringToInt(price);
        validate(priceNum);
        this.price = priceNum;
        this.count = calculateCount(priceNum);
    }
    public int calculateCount(int price){
        return price/1000;
    }
    public int getCount(){
        return count;
    }
    public int getPrice(){
        return price;
    }
    public int convertStringToInt(String price){
        try {
            return Integer.parseInt(price);
        } catch (NumberFormatException e) {
            System.err.println("[ERROR] 숫자로만 입력해주세요.");
            throw e;
        }
    }

    public void validate(int priceNum){
        validateRange(priceNum);
        validateUnit(priceNum);
    }
    public void validateRange(int priceNum){
        if(priceNum<=0){
            throw new IllegalArgumentException("0원 이하는 구매가 불가능합니다");
        }
    }
    public void validateUnit(int priceNum){
        if(priceNum % LOTTO_UNIT != 0){
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위로 입력되어야 합니다.");
        }
    }
}
