package inputView;

import model.LottoNumbers;

import java.util.List;

public class OutputView {
    public void printInputPrice() {
        System.out.print("구입 금액을 입력해 주세요: ");
    }

    public void printInvalidNumber() {
        System.out.println("숫자만 입력해 주세요");
    }

    public void printInvalidPrice() {
        System.out.println("0원 이상을 입력해 주세요");
    }

    public void printPriceValue(int price) {
        System.out.println(price);
    }

    public void printBuyCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    public void printInputLastLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    public void printLottos(List<LottoNumbers> lottos) {
        for (LottoNumbers lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printLotteryStatistics(String three, String four, String five, String six, String amount) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println("3개 일치 (5000원)- " + three + "개");
        System.out.println("4개 일치 (50000원)- " + four + "개");
        System.out.println("5개 일치 (150000원)- " + five + "개");
        System.out.println("6개 일치 (2000000000원)- " + six + "개");
        System.out.println("총 수익률은 " + amount + "입니다.");
    }
}
