package view;

import domain.Lotto;
import  domain.Wallet;

public class ResultView {
    public static void printResult(Wallet wallet){
        System.out.println(wallet.getLottoCollection().size() + "개를 구매했습니다.");
        for(Lotto lotto : wallet.getLottoCollection()){
            System.out.println(lotto);
        }
    }
}
