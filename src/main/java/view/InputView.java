package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    Scanner input = new Scanner(System.in);

    public int inputPrice() {
        System.out.println("구입 금액을 입력해주세요.");
        return input.nextInt();
    }

    public String inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요");
        return input.next();
    }

    public int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return input.nextInt();
    }

    public int inputPassiveLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return input.nextInt();
    }

    public List<String> inputPassiveLottoNumber(int lottoCount) {
        List<String> PassiveNumberStringList = new ArrayList<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int count = 0; count < lottoCount; count++) {
            PassiveNumberStringList.add(input.next());
        }
        return PassiveNumberStringList;
    }
}
