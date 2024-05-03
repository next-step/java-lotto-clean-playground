package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final static String INPUT_WINNING_NUMBERS="지난 주 당첨 번호를 입력해 주세요.";
    private final static String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private static final Scanner scanner = new Scanner(System.in);


    public int inputPrice(){
        System.out.println(INPUT_PRICE);
        int price = scanner.nextInt();
        scanner.nextLine();
        return price;
    }

    public List<Integer> inputWinningNumbers(){
        System.out.println();
        System.out.println(INPUT_WINNING_NUMBERS);
        List<Integer> winningNumbers=new ArrayList<>();
        String input= scanner.nextLine().trim();
        String[] numbers=input.split(", ");
        for(String num:numbers)
            if(!num.isEmpty())
                winningNumbers.add(Integer.parseInt(num));
        return winningNumbers;
    }
}