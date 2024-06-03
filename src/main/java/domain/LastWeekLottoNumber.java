package domain;

import java.util.ArrayList;
import java.util.List;

public class LastWeekLottoNumber {

    private static final String SPLIT_STRING_DELIMITER = ",";

    private final List<Integer> lastWeekLottoNumber;

    public LastWeekLottoNumber(String LastWeekLottoNumber, int bonusBall) {
        this.lastWeekLottoNumber = makeLastWeekLottoNumberList(LastWeekLottoNumber, bonusBall);
    }

    public List<Integer> getLastWeekLottoNumber() {
        return lastWeekLottoNumber;
    }

    private List<Integer> makeLastWeekLottoNumberList(String inputLastWeekLottoNumber, int bonusBall) {
        List<String> inputLottoNumber = splitStringLottoNumber(inputLastWeekLottoNumber);
        List<Integer> lastWeekLottoNumber = new ArrayList<>();
        for (String lottoNumber : inputLottoNumber) {
            lastWeekLottoNumber.add(Integer.parseInt(lottoNumber));
        }
        lastWeekLottoNumber.add(bonusBall);
        return lastWeekLottoNumber;
    }

    private List<String> splitStringLottoNumber(String inputLastWeekLottoNumber) {
        String[] lottoNumber = inputLastWeekLottoNumber.split(SPLIT_STRING_DELIMITER);
        return List.of(lottoNumber);
    }
}