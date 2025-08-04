package controller;

import model.AutoLotto;
import model.Lotto;
import model.LottoList;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();
    private AutoLotto autoLotto;
    private LottoList lottoList;


    public LottoController(){
        lottoStart();
    }
    public void lottoStart() {
        int price = inputMoney();
        viewLotto(price);
    }

    public int inputMoney(){
        return inputView.inputPrice();
    }
    public void viewLotto(int price){
        int count = price/1000;
        outputView.countPrint(count);
        lottoList = makeLottolist(count);
    }
    public LottoList makeLottolist(int count){
        for(int i=0; i<count; i++){
            lottoList = new LottoList(makeLotto());
        }
        return lottoList;
    }
    public Lotto makeLotto(){
        List<Integer> lotto = new ArrayList<>();
        AutoLotto autoLotto = new AutoLotto();
        lotto = autoLotto.getAutoLotto();
        outputView.lottoPrint(lotto);
        return new Lotto(lotto);
    }
}
