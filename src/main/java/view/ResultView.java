package view;

import java.util.List;

public class ResultView {
    public void printPurchaseCount(int count){
        System.out.println("\n" + count + "개를 구매했습니다.");
    }
    public void printLottoNumbers(List<Integer> numbers){

            System.out.println(numbers);

    }
    public void printWinningStatics(int num, int price, int count){
        System.out.println(num + "개 일치 (" + price + ")- " + count + "개");
    }
    public void printYield(double yield, boolean sign){
        System.out.print("총 수익률은 " + yield + "입니다.");
        if (sign){
            System.out.println("(기준이 1이기 때문에 결과적으로 이득이라 더 뽑아도 된다는 의미임)");
        }
        if (!sign){
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }

    }

}
