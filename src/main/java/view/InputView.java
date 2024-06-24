package view;

import domain.Price;
import java.util.InputMismatchException;
import java.util.Scanner;
import util.Errors;

public class InputView {

    private int getUserIntegerInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        }  catch (InputMismatchException e) {
            throw new IllegalArgumentException(Errors.INPUT_IS_NOT_INTEGER);
        }
    }

    public Price getPrice() {
        final int userIntegerInput = getUserIntegerInput();
        return new Price(userIntegerInput);
    }
}
