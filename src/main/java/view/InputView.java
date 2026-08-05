package view;

import model.Lotto;

import java.util.*;

public class InputView {
    public int inputCost() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public List<Integer> inputWinningNums(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        List<Integer> nums= Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .toList();

        return nums;
    }
    public int inputBonusBall(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("보너스 볼을 입력해 주세요.");

        return scanner.nextInt();
    }

    public int inputPassiveAmount(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");

        return scanner.nextInt();
    }

    public List<Integer> inputPassiveLotto(){
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");

        Scanner scanner = new Scanner(System.in);

        List<Integer> nums=Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .toList();

        return nums;
    }

}
