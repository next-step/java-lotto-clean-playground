package view;

import domain.LottoNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    public static int inputPrice() {

        System.out.println("구입금액을 입력해 주세요.");
        int price = scanner.nextInt();

        return price;
    }

    public static List<Integer> inputWinning() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String win = scanner.nextLine();

        String[] wins = win.split(",");
        List<Integer> nums = new ArrayList<>();

        for (int i = 0; i < wins.length; i++) {
            nums.add(Integer.parseInt(wins[i]));
        }
        return nums;
    }

    public static int inputBonusBall() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        int bonusBall = scanner.nextInt();

        return bonusBall;
    }

    public static int inputPassiveCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        int count = scanner.nextInt();
        return count;
    }

    public static List<List<LottoNumber>> inputPassiveLotto(int manualCount) {
        scanner.nextLine();

        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<List<LottoNumber>> passiveLottos = new ArrayList<>();

        for (int i = 0; i < manualCount; i++) {
            passiveLottos.add(inputManualLotto());
        }
        return passiveLottos;
    }

    private static List<LottoNumber> inputManualLotto() {
        String input = scanner.nextLine();
        String[] numbers = input.split(",");

        List<LottoNumber> lotto = new ArrayList<>();

        for (String number : numbers) {
            lotto.add(LottoNumber.from(Integer.parseInt(number.trim())));
        }

        return lotto;
    }
}
