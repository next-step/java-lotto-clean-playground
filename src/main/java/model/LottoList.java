package model;

import view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoList {
    List<Lotto> lottoList = new ArrayList<>();
    private int totalPrice;

    public LottoList(){

    }
    public void setLottoList(Lotto lotto){
        lottoList.add(lotto);
    }

    public List<Lotto> getLottoList(){
        return lottoList;
    }
    public void setTotalPrice(int reward){
        this.totalPrice+=reward;
    }
    public int getTotalPrice(){
        return totalPrice;
    }
}
