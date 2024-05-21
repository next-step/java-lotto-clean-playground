package domain;

import java.util.ArrayList;
import java.util.List;

public class LastWeekLottoNumber {

    private static final String SPLIT_STRING_DELIMITER = ",";
    private final List<Integer> lastWeekLottoNumber;
    private final String inputLastWeekLottoNumber;

    public List<Integer> getLastWeekLottoNumber() {
        return lastWeekLottoNumber;
    }

    public LastWeekLottoNumber(String LastWeekLottoNumber) {
        this.inputLastWeekLottoNumber = LastWeekLottoNumber;
        this.lastWeekLottoNumber = makeLastWeekLottoNumberList();
    }

    private List<Integer> makeLastWeekLottoNumberList() {
        List<String> inputLottoNumber = splitStringLottoNumber();
        List<Integer> lastWeekLottoNumber = new ArrayList<>();
        for (String lottoNumber : inputLottoNumber) {
            lastWeekLottoNumber.add(Integer.parseInt(lottoNumber));
        }
        return lastWeekLottoNumber;
    }

    private List<String> splitStringLottoNumber() {
        String[] lottoNumber = inputLastWeekLottoNumber.split(SPLIT_STRING_DELIMITER);
        return List.of(lottoNumber);
    }
}
