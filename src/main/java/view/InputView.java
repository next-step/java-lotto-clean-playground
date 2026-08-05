package view;

import model.Lotto;

import java.util.*;

public class InputView {

    private Scanner scanner = new Scanner(System.in);

    public int inputCost() {
        System.out.println("구입금액을 입력해 주세요.");

        int v = scanner.nextInt();
        scanner.nextLine(); // 남은 개행 소비
        return v;
    }

    public List<Integer> inputWinningNums() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        List<Integer> nums = Arrays.stream(scanner.nextLine().split(", "))
            .map(Integer::parseInt)
            .toList();

        return nums;
    }

    public int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");

        int v = scanner.nextInt();
        scanner.nextLine(); // 남은 개행 소비
        return v;
    }

    public int inputPassiveAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        int v = scanner.nextInt();
        scanner.nextLine(); // 남은 개행 소비
        return v;
    }

    public List<Integer> inputPassiveLotto() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        List<Integer> nums = Arrays.stream(scanner.nextLine().split(", "))
            .map(Integer::parseInt)
            .toList();

        return nums;
    }

}
