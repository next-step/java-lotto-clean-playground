package view;

import java.util.Scanner;

public class InputView {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static int inputPurchaseMoney(){
        int PurchaseMoneny = SCANNER.nextInt();
        return PurchaseMoneny;
    }
}
