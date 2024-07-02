package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumberList {

    private static final String SPLIT_CHAR = ",";
    private List<Integer> numbers = new ArrayList<>();

    public LottoNumberList(String inputWinNumber) {
        numbers = splitString(inputWinNumber);
    }

    public List<Integer> splitString(String inputWinNumber) {
        String[] splitNumbers = inputWinNumber.split(SPLIT_CHAR);
        for (String inputNumber : splitNumbers) {
            int number = Integer.parseInt(inputNumber);
            validateNumberRange(number);
            numbers.add(number);
        }
        return numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void validateNumberRange(int number) {
        if (number >= 45) {
            throw new IllegalArgumentException("잘못된 번호가 입력되었습니다.");
        }
    }
}
