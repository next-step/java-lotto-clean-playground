package view;

import domain.Lotto;
import domain.Lottos;

import java.util.List;

public class OutputView {

    public void printGetLottoMoney(){
        System.out.println("구입금액을 입력해 주세요.");
    }

    public void printLottoCount(int lottoCount){
        System.out.println("\n"+lottoCount+"개를 구매했습니다.");
    }

    public void printSumOfLotto(Lottos lottos){
        List<Lotto> lottoList = lottos.getLottos();
        for(Lotto lottoNumber: lottoList){
            System.out.println(lottoNumber.getLottoNumber().toString());
        }
    }

    public void LastWeekLottoNumber(){
        System.out.println("\n"+"지난 주 당첨 번호를 입력해 주세요.");
    }
}
