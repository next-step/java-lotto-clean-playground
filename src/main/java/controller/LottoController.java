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
    private LottoList lottoList=new LottoList();

    private LottoService lottoService=new LottoService();

    public LottoController(){
        lottoStart();
    }
    public void lottoStart() {
        int price = inputMoney();
        int manualPrice= inputManual();
        viewLotto(price,manualPrice);
        List<Integer> winningNums=inputView.inputWinningNumbers();
        lottoList.setTotalPrice(price);//넣어야함
        int bonusBall=inputView.inputBonusBall();
        lottoService.calculateWinningNumbers(winningNums,lottoList,bonusBall);
    }

    public int inputMoney(){
        return inputView.inputPrice();
    }
    public int inputManual(){
        return inputView.inputManualNum();
    }
    public void viewLotto(int price,int manualPrice){
        int count = price/1000-manualPrice;
        lottoList = generateLottoList(count,manualPrice);
        outputView.countPrint(manualPrice,count);
        printLottoList(lottoList);//생성된 후 출력
    }

    public void printLottoList(LottoList lottoList){
        for(Lotto lotto:lottoList.getLottoList())
            System.out.println(lotto.getNumbers());
    }

    public Lotto makeLotto(){
        List<Integer> lotto = new ArrayList<>();
        AutoLotto autoLotto = new AutoLotto();
        lotto = autoLotto.getAutoLotto();
        return new Lotto(lotto);
    }
    public LottoList generateLottoList(int count,int manualPrice){
        inputView.InputManualLottoStart();
        for(int i=0;i<manualPrice;i++){
            List<Integer> lottoNums=inputView.inputManualLottoNum();
            Lotto manualLotto=new Lotto(lottoNums);
            lottoList.setLottoList(manualLotto);
        }
        for(int j=0; j<count; j++){
            lottoList.setLottoList(makeLotto());
        }
        return lottoList;
    }
}