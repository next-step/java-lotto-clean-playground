package domain;

import java.util.ArrayList;
import java.util.List;

public class LastWeekLottoNumber {

    private static final String SPLIT_STRING_DELIMITER = ",";
    private final List<Integer> lastWeekLottoNumber = new ArrayList<>();
    private final String inputLastWeekLottoNumber;

    public LastWeekLottoNumber(String LastWeekLottoNumber) {
        this.inputLastWeekLottoNumber = LastWeekLottoNumber;
    }

    private List<String> splitStringLottoNumber() {
        String[] lottoNumber = inputLastWeekLottoNumber.split(SPLIT_STRING_DELIMITER);
        return List.of(lottoNumber);
    }

    public void makeLastWeekLottoNumberList() {
        List<String> inputLottoNumber = splitStringLottoNumber();
        for (String lottoNumber : inputLottoNumber) {
            lastWeekLottoNumber.add(Integer.parseInt(lottoNumber));
        }
    }

    public List<Integer> getLastWeekLottoNumber() {
        return lastWeekLottoNumber;
    }
}
