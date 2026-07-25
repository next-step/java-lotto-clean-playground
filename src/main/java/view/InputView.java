package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    public static int inputMoney(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해주세요");
        return scanner.nextInt();
    }

    public static List<Integer> inputWinningNumbers(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("지난 주 당첨 번호를 입력하세요");
        String inputWinningNumbers = scanner.nextLine();
        return winningNumbers(inputWinningNumbers);
    }

    private static List<Integer> winningNumbers(String inputWinningNumbers){
        List<String> element = List.of(inputWinningNumbers.split(","));
        List<Integer> change = new ArrayList<>();
        for (String s : element) {
            change.add(Integer.parseInt(s.trim()));
        }
        return change;
    }
}
