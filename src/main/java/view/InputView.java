package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int purchaseAmount(){
      System.out.println("\n구입금액을 입력해주세요");
      int amount = scanner.nextInt();
      scanner.nextLine();
      System.out.println();
      return amount;
    }

    public static List<Integer> winningNumbers(){
      System.out.println("\n지난 주 당첨 번호를 입력하세요");
      String inputWinningNumbers = scanner.nextLine();
      System.out.println();
      return parseWinningNumbers(inputWinningNumbers);
    }

    private static List<Integer> parseWinningNumbers(String inputWinningNumbers){
      List<String> tokens = List.of(inputWinningNumbers.split(","));
      return tokens.stream().map(token ->Integer.parseInt(token.trim())).toList();
    }
}
