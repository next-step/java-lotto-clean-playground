package inputView;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public String inputPrice() {
        return scanner.nextLine();
    }

    public String inputLastLotto() {
        return scanner.nextLine();
    }
}
