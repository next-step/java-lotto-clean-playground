package view;

import java.util.Scanner;

public class InputView {

    private final Scanner input = new Scanner(System.in);

    public int getLottoMoney() {
        return input.nextInt();
    }

    public int getPassiveLottoCount() {
        return input.nextInt();
    }

    public String inputLottoNumber() {
        return input.next();
    }

    public int inputBonusBall() {
        return input.nextInt();
    }
}
