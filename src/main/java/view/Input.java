package view;

import java.util.*;

public class Input {

    private static Scanner scanner = new Scanner(System.in);

    public static int moneyInput(){
        System.out.println("구입금액을 입력해 주세요.");
        int money = scanner.nextInt();
        System.out.println();
        return money/1000;
    }

    public static int manualNumInput(){
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        int manual = scanner.nextInt();
        System.out.println();
        scanner.nextLine();
        return manual;
    }

    public static List<String> manualLottoInput(int n) {
        if (n == 0)
            System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> manualLotto = Arrays.asList(scanner.nextLine().split(","));
        for(int i = 0; i < manualLotto.size(); i++){
            manualLotto.set(i, manualLotto.get(i).trim());
        }
        return manualLotto;
    }

    public static List<String> autoLottoInput() {
        List<String> lottoRange = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoRange.add(String.valueOf(i));
        }
        Collections.shuffle(lottoRange);
        return lottoRange.subList(0, 6);
    }

    public static List<String> lastWeekLottoNum(){
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        List<String> LastWeekLotto = Arrays.asList(scanner.nextLine().split(","));
        for(int i = 0; i < LastWeekLotto.size(); i++){
            LastWeekLotto.set(i, LastWeekLotto.get(i).trim());
        }
        return LastWeekLotto;
    }

    public static String bonusBall(){
        System.out.println("\n보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
