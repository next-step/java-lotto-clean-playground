package view;

import model.Lottos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LottoInput {

    public static int inputBalance() {
        Scanner sc = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        return sc.nextInt();
    }

    public static List<Integer> lottoAnswer() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] ans = str.split(", ");
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            result.add(Integer.parseInt(ans[i]));
        }
        return result;
    }

    public static int bonusAnswer(Lottos lottos) {
        System.out.println("보너스 볼을 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        int bonus = sc.nextInt();
        lottos.setBonusBall(bonus);
        return bonus;
    }

    public static int manual() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static List<Integer> manualLottoInput() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] ans = str.split(", ");
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            result.add(Integer.parseInt(ans[i]));
        }
        return result;
    }
}
