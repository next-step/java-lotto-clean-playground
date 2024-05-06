package model;

import java.util.List;
import java.util.function.Function;

public enum CorrectNum { //개수를 전달받아서 총 수익금 계산 하기?
    ZERO(0, 0),
    ONE(1, 0),
    TWO(2, 0),
    THREE(3, 5000),
    FOUR(4, 50000),
    FIVE_NOT(5, 1500000),
    FIVE(5, 30000000),
    SIX(6, 2000000000);
    private int num;
    private int money;

    CorrectNum(int num, int money) {
        this.num = num;
        this.money = money;
    }

    public int getNum() {
        return num;
    }

    public int getMoney() {
        return money;
    }

    public static List<CorrectNum> getList() {
        return List.of(CorrectNum.values()); //{ZERO,ONE, ...
    }
}
