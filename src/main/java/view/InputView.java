package view;

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
        scanner.nextLine();
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String win = scanner.nextLine();

        String[] wins = win.split(",");
        List<Integer> nums = new ArrayList<>();

        for (int i = 0; i < wins.length; i++) {
            nums.add(Integer.parseInt(wins[i]));
        } return nums;
    }
}
