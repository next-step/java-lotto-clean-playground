package view;

import java.util.List;

public class ResultView {
    public void printPurchaseCount(int count){
        System.out.println("\n" + count + "개를 구매했습니다.");
    }
    public void printLottoNumbers(List<Integer> numbers){

            System.out.println(numbers);

    }
}
