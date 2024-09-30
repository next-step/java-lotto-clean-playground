package view;

public class OutputView {

    public OutputView() {
    }

    public void printPurchasedCount(int manualCount, int autoCount) {
        if (manualCount > 0 && autoCount > 0) {
            System.out.print("수동으로 " + manualCount + "장, " + "자동으로 " + autoCount + "개를");
        }

        if (manualCount > 0) {
            System.out.print("수동으로 " + manualCount + "장을");
        }

        if (autoCount > 0) {
            System.out.print("자동으로 " + autoCount + "개를");
        }

        System.out.println(" 구매했습니다.");
    }
}
