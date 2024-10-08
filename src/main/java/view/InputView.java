package view;

import domain.Lotto;
import domain.Lottos;
import utils.NumberFormatter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class InputView {
    private static Scanner input = new Scanner(System.in);

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        try {
            int money = input.nextInt();
            input.nextLine();
            return money;
        } catch (InputMismatchException e){
            input.nextLine();
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }

    public static int inputManualCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            int manualCount = input.nextInt();
            input.nextLine();
            return manualCount;
        } catch (InputMismatchException e){
            input.nextLine();
            throw new IllegalArgumentException("숫자를 입력해야 합니다.");
        }
    }

    public static Lottos inputManualNumber(int inputManualLottoCount) {
        List<Lotto> lottos = new ArrayList<>();

        System.out.println("수동으로 구매할 로또 번호를 입력해 주세요.");
        for (int i = 0; i < inputManualLottoCount; i++) {
            lottos.add(new Lotto(NumberFormatter.formNumbers(input.nextLine())));
        }

        return new Lottos(lottos);
    }

    public static String inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return input.nextLine();
    }

    public static int inputBonus() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return input.nextInt();
    }
}
