package view;

import constants.ErrorMessageConstants;
import constants.ScriptConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final String NUMBER_DELIMITER = ",";
    Scanner scanner;
    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getSingleIntegerFromUserAfterShowingAScript(String script) {
        System.out.println(script);
        String userInput = scanner.nextLine();
        return convertStringToInteger(userInput);
    }

    public List<List<Integer>> getManuallyPurchasedLottoNumbers(int count) {
        System.out.println(ScriptConstants.INPUT_ENTER_MANUAL_PURCHASE_LOTTO_SCRIPT);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < count; i ++) {
            String userInput = scanner.nextLine();
            List<Integer> userInputAsLottoNumber = this.parseUserInputIntoLottoNumbers(userInput);
            result.add(userInputAsLottoNumber);
        }

        return result;
    }

    public List<Integer> getWinningNumbers() {
        System.out.println(ScriptConstants.INPUT_ENTER_WINNING_NUMBER_SCRIPT);
        return this.parseUserInputIntoLottoNumbers(scanner.nextLine());
    }

    protected List<Integer> parseUserInputIntoLottoNumbers(String userInput) {
        List<String> userInputParsed = parseByDelimiter(userInput);
        List<Integer> result = new ArrayList<>();

        for (String currentToken: userInputParsed) {
            result.add(this.convertStringToInteger(currentToken));
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
