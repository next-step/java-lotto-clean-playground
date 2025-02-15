package view;

import domain.Lotto;
import  domain.Wallet;
import domain.WinningNumbers;

public class ResultView {
    public static void printResultOfPurchase(Wallet wallet){
        System.out.println(wallet.getLottoCollection().size() + "개를 구매했습니다.");
        for(Lotto lotto : wallet.getLottoCollection()){
            System.out.println(lotto);
        }
        System.out.print("\n");
    }

    public static void printResultOfWinning(Wallet wallet, WinningNumbers winningNumbers){
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + wallet.countThirdPlace(winningNumbers) +"개");
        System.out.println("4개 일치 (50000원)- " + wallet.countForthPlace(winningNumbers) +"개");
        System.out.println("5개 일치 (1500000원)- " + wallet.countFifthPlace(winningNumbers) +"개");
        System.out.println("6개 일치 (2000000000원)- " + wallet.countSixthPlace(winningNumbers) +"개");
        System.out.println("총 수익률은 "+wallet.getIncomeRate()+"입니다.");
    }
}
