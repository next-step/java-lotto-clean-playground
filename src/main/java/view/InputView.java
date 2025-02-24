package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner sc = new Scanner(System.in);

    public static int getMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        int money = sc.nextInt();
        sc.nextLine();
        // ADD: 1000원 보다 적을때, 오버플로우 감지, 000으로 안끝나는 경우.
        return money;
    }

    public static int getManualNumberCount() {
        System.out.println("\n수동으로 구매할 로또 수를 입력해 주세요.");
        int money = sc.nextInt();
        sc.nextLine();

        return money;
    }

    public static List<List<Integer>> getManualLottoes(int purchasedMaunalCount) {
        List<List<Integer>> manualLottoes = new ArrayList<>();

        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");

        for (int count = 0; count < purchasedMaunalCount; count++) {
            manualLottoes.add(getLottoLine());
        }

        return manualLottoes;
    }

    public static List<Integer> getWinningList() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        return getLottoLine();
    }

    private static List<Integer> getLottoLine() {
        String input = sc.nextLine();
        String[] tokens = input.split("\\s*,\\s*");

        List<Integer> lottoList = new ArrayList<>();

        for (String token : tokens) {
            lottoList.add(Integer.parseInt(token.trim()));
        }

        return lottoList;
    }

    public static int getBonusNumber() {
        System.out.println("\n보너스 볼을 입력해 주세요.");
        // ADD: 검증 로직(1~45인지)
        return sc.nextInt();
    }
}
