package view;

import java.util.List;

public class OutputView {

    public static void inputMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }


    public static void inputManualMessage() {
        System.out.println();
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }


    public static void printInputinfo() {
        System.out.println();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }

    public static void printLottoCountInfo(int manualCount, int autoCount) {
        System.out.println();
        System.out.println("수동으로" + manualCount + "장, 자동으로" + autoCount + "개를 구매했습니다.");
    }


    public static void printTotalNumbers(List<List<Integer>> resultArrays) {
        for (List<Integer> resultArray : resultArrays) {
            System.out.println(resultArray);
        }
    }

    public static void inputAnswerMessage() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }


    public static void inputBonusMessage() {
        System.out.println();
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    public static void resultsMessage() {
        System.out.println();
        System.out.println("당첨 통계\n" +
                "---------");
    }
}
