package domain;

public class Money{
    private int money;

    public Money(int money) throws IllegalArgumentException{
        if(money < 0){
            throw new IllegalArgumentException("금액은 양수여야 합니다.");
        }
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }
}
