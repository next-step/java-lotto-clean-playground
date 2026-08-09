package view;

import domain.Lotto;
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

    public static Lotto inputWinning() {
        System.out.println();
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        String win = scanner.nextLine();
        String[] wins = win.split(",");

        List<LottoNumber> nums = new ArrayList<>();

        for (int i = 0; i < wins.length; i++) {
            nums.add(LottoNumber.from(Integer.parseInt(wins[i])));
        }
        return Lotto.from(nums);
    }

    public static LottoNumber inputBonusBall() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        int bonusBall = scanner.nextInt();

        return LottoNumber.from(bonusBall);
    }

    public static int inputPassiveCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        int count = scanner.nextInt();
        return count;
    }

    public static List<Lotto> inputPassiveLotto(int manualCount) {
        scanner.nextLine();

        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> passiveLottos = new ArrayList<>();

        for (int i = 0; i < manualCount; i++) {
            passiveLottos.add(inputManualLotto());
        }
        return passiveLottos;
    }

    private static Lotto inputManualLotto() {
        String input = scanner.nextLine();
        String[] numbers = input.split(",");

        List<LottoNumber> lotto = new ArrayList<>();

        for (String number : numbers) {
            lotto.add(LottoNumber.from(Integer.parseInt(number.trim())));
        }

        return Lotto.from(lotto);
    }
}
