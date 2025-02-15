package domain;

import java.util.ArrayList;

public class Wallet {
    private final Money money;
    private final ArrayList<Lotto> lottoCollection = new ArrayList<>();

    public Wallet(Money money) {
        this.money = money;

    }

    public void buyAutomatedLotto(){
        //살 수 있는 로또 개수 구하기
        //개수만큼 생성하기
        for(int i=0; i<this.calculateMaximumNumberOfLottos(); i++){
            lottoCollection.add(new Lotto());
        }
    }

    public ArrayList<Lotto> getLottoCollection() {
        return lottoCollection;
    }

    private int calculateMaximumNumberOfLottos(){
        return this.money.getMoney() / 1000;
    }

}

