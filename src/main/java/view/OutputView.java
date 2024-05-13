package view;

import java.util.List;

public class OutputView {

    public void printGetLottoMoney(){
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printLottoCount(int lottoCount){
        System.out.println("\n"+lottoCount+"개를 구매했습니다.");
    }

    public void printSumOfLotto(List<List<Integer>> sumOfLotto){
        for(List<Integer> list: sumOfLotto){
            System.out.println(list.toString());
        }
    }
}
