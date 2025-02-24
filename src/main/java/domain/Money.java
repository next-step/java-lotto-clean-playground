package domain;

public class Money{
    private long money;

    public Money(long money) throws IllegalArgumentException{
        if(money < 0){
            throw new IllegalArgumentException("금액은 양수여야 합니다.");
        }
        this.money = money;
    }

    public long getMoney() {
        return money;
    }

    public void addMoney(long amount) {
        this.money += amount;
    }

    public void useMoney(long amount) {
        this.money -= amount;
    }
}
