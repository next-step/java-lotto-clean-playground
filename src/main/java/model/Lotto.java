package model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final int balance;
    private final int count;
    private final List<OneLotto> myLottos;

    public Lotto(int balance, int count, List<OneLotto> myLottos) {
        this.balance = balance;
        this.count = count;
        this.myLottos = myLottos;
    }

    public int getBalance() {
        return balance;
    }

    public List<OneLotto> getMyLottos() {
        return myLottos;
    }


}
