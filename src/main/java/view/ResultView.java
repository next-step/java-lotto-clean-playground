package view;

import domain.Lotto;
import  domain.Wallet;

public class ResultView {
    public static void printResultOfPurchase(Wallet wallet){
        System.out.println(wallet.getLottoCollection().size() + "개를 구매했습니다.");
        for(Lotto lotto : wallet.getLottoCollection()){
            System.out.println(lotto);
        }
        System.out.print("\n");
    }

    public static void printResultOfWinning(Wallet wallet){
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + wallet.getLottoStats().getCountFifth() +"개");
        System.out.println("4개 일치 (50000원)- " + wallet.getLottoStats().getCountFourth() +"개");
        System.out.println("5개 일치 (1500000원)- " + wallet.getLottoStats().getCountThird() +"개");
        System.out.println("5개 일치, 보너스 볼 일치 (30000000원)- " + wallet.getLottoStats().getCountSecond() +"개");
        System.out.println("6개 일치 (2000000000원)- " + wallet.getLottoStats().getCountFirst() +"개");
        System.out.println("총 수익률은 "+wallet.getIncomeRate()+"입니다.");
    }
}
