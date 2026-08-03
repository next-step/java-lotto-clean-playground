package view;

import model.Lotto;

import java.util.List;
import java.util.Map;

public class OutputView {
    public void printAmount(int autoAmount, int passiveAmount){
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", passiveAmount, autoAmount);
    }

    public void printLottos(List<Lotto> lottos){
        for(Lotto lotto : lottos){
            printLotto(lotto);
        }
    }

    public void printLotto(Lotto lotto){
        System.out.println(lotto);
    }

    public void cycle(Map<Integer,Integer> staticWin){
        System.out.println("당첨통계");
        System.out.println("---------");
        staticWin.forEach((matchCount,winCount)->{
            totalPrize(matchCount,winCount);
        });

    }
    public void totalPrize(int matchCount,int winCount){
        if(matchCount==3){
            System.out.printf("%d개 일치 (5000원) - %d개\n",matchCount,winCount);
        }

        if(matchCount==4){
            System.out.printf("%d개 일치 (50000원) - %d개\n",matchCount,winCount);
        }

        if(matchCount==5){
            System.out.printf("%d개 일치 (1500000원) - %d개\n",matchCount,winCount);
        }

        if(matchCount==7){
            System.out.printf("5개 일치, 보너스 볼 일치 (30000000원) - %d개\n",winCount);
        }

        if(matchCount==6){
            System.out.printf("%d개 일치 (2000000000원) - %d개\n",matchCount,winCount);
        }
    }
    public void printRatio(float ratio){
        System.out.printf("총 수익률은 %f입니다",ratio);
    }
}
