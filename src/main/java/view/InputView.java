package view;

import constants.ErrorMessageConstants;
import constants.ScriptConstants;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {
    private final String NUMBER_DELIMITER = ",";
    Scanner scanner;
    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getUserCashInput() {
        System.out.println(ScriptConstants.INPUT_CASH_SCRIPT);
        String userInput = scanner.nextLine();
        try {
            return Integer.parseInt(userInput.strip());
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessageConstants.NOT_A_SINGLE_NUMBER);
        }
    }

    public List<Integer> getWinningNumbers() {
        System.out.println(ScriptConstants.INPUT_ENTER_WINNING_NUMBER_SCRIPT);
        List<String> userInputs = parseByDelimiter(scanner.nextLine());
        List<Integer> result = new ArrayList<>();

        for (String userInput: userInputs) {
            result.add(this.convertStringToInteger(userInput));
        }

        return result;
    }

    protected List<String> parseByDelimiter(String userInput) {
        List<String> result = new ArrayList<String>();

        for (String x: userInput.split(NUMBER_DELIMITER)) {
            String strippedName = x.strip();
            result.add(strippedName);
        }

        return result;
    }

    protected int convertStringToInteger(String stringToConvert) {
        try {
            return Integer.parseInt(stringToConvert);
        } catch (Exception e) {
            throw new IllegalArgumentException(ErrorMessageConstants.NOT_A_NUMBER);
        }
    }
}
