package model;

import java.util.ArrayList;
import java.util.List;

import static model.RandomNumGenerator.randomNumGenerate;

public class Lotto {
    private final int balance;
    private int count;
    private List<LottoNumber> myLottos = new ArrayList<>();
    private RankRepository rankRepository = new RankRepository();

    public Lotto(int balance) {
        this.balance = balance;
        makeCount();
    }

    private void makeCount() {
        this.count = balance/1000;
    }

    public void makeMyLottos() {
        for(int i = 0; i<count;i++){
            LottoNumber lottoNumberTmp = new LottoNumber(randomNumGenerate());
            this.myLottos.add(lottoNumberTmp);
        }
    }

    public int getCount() {
        return count;
    }

    public List<LottoNumber> getMyLottos() {
        return myLottos;
    }
}
