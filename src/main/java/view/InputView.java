package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import domain.Lotto;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int LOTTO_PRICE = 1_000;

    public static int inputPurchasePrice() {
        System.out.println("구입 금액을 입력해 주세요.");
        int a = scanner.nextInt() / LOTTO_PRICE;
        scanner.nextLine();
        return a;
    }

    public static int inputNumberOfManualLotto() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int numberOfLotto = scanner.nextInt();
        scanner.nextLine();
        return numberOfLotto;
    }

    public static String inputManualLottoNumber() {

        return scanner.nextLine();
    }

    public static String inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextInt();
    }

}
