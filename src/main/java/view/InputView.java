package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import util.Errors;

public class InputView {

    private final static String DELIMITER_OF_NUMBERS = ",";

    private Scanner scanner;

    private String getUserStringInput() {
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int getUserIntegerInput() {
        try {
            scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException(Errors.INPUT_IS_NOT_INTEGER);
        }
    }

    public List<Integer> getWinningNumbers() {
        final String userStringInput = getUserStringInput();
        return convertStringToList(userStringInput);
    }

    private List<Integer> convertStringToList(String input) {
        List<Integer> numbers = new ArrayList<>();
        final String[] inputNumbers = input.split(DELIMITER_OF_NUMBERS);
        for (String inputNumber : inputNumbers) {
            try {
                int number = Integer.parseInt(inputNumber.trim());
                numbers.add(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(Errors.INPUT_NUMBER_IS_NOT_INTEGER);
            }
        }
        return numbers;
    }
}
