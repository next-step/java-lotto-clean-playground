package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static int inputPurchaseMoney(){
        int purchaseMoneny = SCANNER.nextInt();
        return purchaseMoneny;
    }
    public static List<Integer> inputWinningNumber() {
        String winningNumber = SCANNER.nextLine();
        return parse(winningNumber);
    }

    private static List<Integer> parse(String input) {
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
