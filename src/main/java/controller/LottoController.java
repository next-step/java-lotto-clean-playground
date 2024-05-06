package controller;

import model.AutoLotto;
import model.Lotto;
import model.LottoList;
import model.LottoService;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LottoController {
    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();
    private AutoLotto autoLotto;
    private LottoList lottoList;

    private LottoService lottoService=new LottoService();

    public LottoController(){
        lottoStart();
    }
    public void lottoStart() {
        int price = inputMoney();
        viewLotto(price);
        List<Integer> winningNums=inputView.inputWinningNumbers();
        lottoList.setTotalPrice(price);//넣어야함
        int bonusBall=inputView.inputBonusBall();
        lottoService.calculateWinningNumbers(winningNums,lottoList,bonusBall);
    }

    public int inputMoney(){
        return inputView.inputPrice();
    }
    public void viewLotto(int price){
        int count = price/1000;
        outputView.countPrint(count);
        lottoList = generateLottoList(count);
        //printLottoList(lottoList);//생성된 후 출력
    }
    public Lotto makeLotto(){
        List<Integer> lotto = new ArrayList<>();
        AutoLotto autoLotto = new AutoLotto();
        lotto = autoLotto.getAutoLotto();
        outputView.lottoPrint(lotto);
        return new Lotto(lotto);
    }
    public LottoList generateLottoList(int count){
        lottoList = new LottoList();
        for(int i=0; i<count; i++){
            lottoList.setLottoList(makeLotto());
        }
        return lottoList;
    }
}