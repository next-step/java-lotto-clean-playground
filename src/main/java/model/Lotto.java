package model;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
    private final int balance;
    private int count;
    private List<LottoNumber> lottoNumberList = new ArrayList<>();

    public Lotto(int balance) {
        this.balance = balance;
    }

    public Lotto(int balance, List<LottoNumber> lottoNumberList) {
        this.balance = balance;
        this.lottoNumberList = lottoNumberList;
    }

    public int calcLottoNumbersCount(){
        this.count = balance/1000;
        return count;
    }

    public void generateRandomLottoNumbers() {
        for(int i=0;i<count;i++){
            LottoNumber lottoNumber = new LottoNumber(RandomNumGenerator.randomNumGenerate());
            lottoNumberList.add(lottoNumber);
        }
    }

    public int getBalance() {
        return balance;
    }

    public int getCount() {
        return count;
    }

    public List<LottoNumber> getLottoNumberList() {
        return lottoNumberList;
    }


}
