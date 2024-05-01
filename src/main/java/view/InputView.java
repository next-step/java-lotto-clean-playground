package view;

import java.util.Scanner;

public class InputView {

    private final static String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private static final Scanner scanner = new Scanner(System.in);


    public int inputPrice(){
        System.out.println(INPUT_PRICE);
        int price = scanner.nextInt();
        return price;
    }
}
