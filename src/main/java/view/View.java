package view;

import model.Lotto;

import java.util.*;

public class View {
    private int cost;

    public int inputCost() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        this.cost=scanner.nextInt();
        return cost;
    }

    public int printAmount(int cost){
        int ticketAmount=cost/1000;
        System.out.printf("%d개를 구매했습니다.\n", ticketAmount);
        return ticketAmount;
    }

    public void printLottos(List<Lotto> lottos){
        for(Lotto lotto : lottos){
            printLotto(lotto);
        }
    }

    public void printLotto(Lotto lotto){
            System.out.println(lotto);
    }

    public List<Integer> inputWinningNums(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
         List<Integer> nums=Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .toList();
        return nums;
    }

    public void cycle(Map<Integer,Integer> staticWin){
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
        if(matchCount==6){
            System.out.printf("%d개 일치 (2000000000원) - %d개\n",matchCount,winCount);
        }
    }
}
