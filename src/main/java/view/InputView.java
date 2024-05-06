package view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class InputView {

    private final static String INPUT_WINNING_NUMBERS="지난 주 당첨 번호를 입력해 주세요.";
    private final static String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private static final String INPUT_MANUAL_NUM="수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUM="수동으로 구매할 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL="보너스 볼을 입력해 주세요.";
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

    public int inputBonusBall(){
        System.out.println();
        System.out.println(INPUT_BONUS_BALL);
        int bonusBall= scanner.nextInt();
        scanner.nextLine();
        return bonusBall;
    }

    public int inputManualNum(){
        System.out.println();
        System.out.println(INPUT_MANUAL_NUM);
        int manualNum= scanner.nextInt();
        scanner.nextLine();
        return manualNum;
    }

    public void InputManualLottoStart(){
        System.out.println();
        System.out.println(INPUT_MANUAL_LOTTO_NUM);
    }
    public List<Integer> inputManualLottoNum(){
        List<Integer> lottoNumbers=new ArrayList<>();
        String input= scanner.nextLine().trim();
        String[] numbers=input.split(", ");
        for(String num:numbers)
            if(!num.isEmpty())
                lottoNumbers.add(Integer.parseInt(num));
        return lottoNumbers;
    }
}