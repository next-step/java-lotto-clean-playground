import java.util.List;
import java.util.Scanner;

public class OutputView {
    private static Scanner in = new Scanner(System.in);

    public void printLottos(List<Lotto> lottos){
        System.out.println("개를 구매했습니다.");
        System.out.println(lottos);
    }

    public void printWinningStatistics(){
        System.out.println("당첨통계\n---------");
    }
}
