package view;

import domain.Lotto;

public class ResultView {
    public void printPurchaseCount(int manualCount, int autoCount) {
        System.out.println();
        if (manualCount > 0 && autoCount > 0) {
            System.out.println("수동으로 " + manualCount + "장, 자동으로 " + autoCount + "개를 구매했습니다.");
            return;
        }
        if (manualCount > 0) {
            System.out.println("수동으로 " + manualCount + "장을 구매했습니다.");
            return;
        }
        System.out.println("자동으로 " + autoCount + "개를 구매했습니다.");
    }

    public void printLottoNumbers(Lotto numbers) {
        System.out.println(numbers.getNumbers());
    }

    public void printStatics() {
        System.out.println("\n당첨 통계\n---------");
    }

    public void printWinningStatics(int num, int price, int count, boolean matchBonus) {
        System.out.print(num + "개 일치");
        if (matchBonus) {
            System.out.print(", 보너스 볼 일치");
        }
        if (!matchBonus) {
            System.out.print(" ");
        }
        System.out.println("(" + price + "원)- " + count + "개");
    }

    public void printYield(double yield) {
        System.out.printf("총 수익률은 %.2f입니다.", yield);
        if (yield > 1.00) {
            System.out.println("(기준이 1이기 때문에 결과적으로 이득이라 더 뽑아도 된다는 의미임)");
        }
        if (yield < 1.00) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
        if (yield == 1.00) {
            System.out.println("(기준이 1이기 때문에 본전은 뽑았다는 의미임)");
        }

    }

}
