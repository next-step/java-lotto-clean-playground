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
    private LottoList lottoList;

    public LottoController(){
        lottoStart();
    }
    public void lottoStart() {
        int price = inputMoney();
        viewLotto(price);
        Lotto winningLotto = winnerNumberToInteger();
        int[] match = match(lottoList, winningLotto);
        double rate = calculateRate(match, price);
        outputView.resultPrint(match, rate);
    }

    public int inputMoney(){
        return toIntegerPrice(inputView.inputPrice());
    }
    public int toIntegerPrice(String price){
        return Integer.parseInt(price);
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

    public Lotto winnerNumberToInteger(){
        String winnerNumsStr = inputView.inputWinnerNumber();
        Lotto winningLotto = new Lotto();
        return winningLotto.toIntegerWinner(winnerNumsStr);
    }


    public int [] match(LottoList lottoList, Lotto winningLotto){
        int [] arr = new int[4];
        for(Lotto lotto : lottoList.getLottoList()) {
            int cnt = lotto.winningCalculate(winningLotto);
            if(cnt == 3){
                arr[0] ++;
            }
            else if (cnt == 4){
                arr[1] ++;
            }
            else if(cnt == 5){
                arr[2] ++;
            }
            else if(cnt == 6){
                arr[3] ++;
            }
        }
        return arr;
    }
    public double calculateRate(int arr[], int price){
        int total = arr[0] * 5000 + arr[1] * 50000 + arr[2] * 1500000 + arr[3] * 2000000000;
        double rate = (double) total / (double) price;
        return rate;
    }

}
