package View;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner scanner = new Scanner(System.in);

    public int getMoney() {
        return scanner.nextInt();
    }



    public String[] getJackpotNumber() {
        return scanner.nextLine().split(",");
    }

    public void resetBuffer()
    {
        scanner.nextLine();
    }

    public int getManualLottoCount() {
        return scanner.nextInt();
    }

    public String manualLotto() {

        return scanner.nextLine();
    }

    public int getBonus() {
        return scanner.nextInt();
    }


}
