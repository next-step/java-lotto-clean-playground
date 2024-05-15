package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoNumber {

    private static final String SPLIT_STRING_DELIMITER = ",";
    private final String inputLastWeekLottoNumber;

    public LottoNumber(String LastWeekLottoNumber){
        this.inputLastWeekLottoNumber = LastWeekLottoNumber;
    }

    private List<String> splitStringLottoNumber(){
        String[] lottoNumber = inputLastWeekLottoNumber.split(SPLIT_STRING_DELIMITER);
        return List.of(lottoNumber);
    }

    public List<Integer> makeLottoNumberList(){
        List<String> inputLottoNumber = splitStringLottoNumber();
        List<Integer> lottoNumberList = new ArrayList<>();
        for(String lottoNumber : inputLottoNumber){
            Integer elementLottoNumber = Integer.parseInt(lottoNumber);
            lottoNumberList.add(elementLottoNumber);
        }
        return lottoNumberList;
    }
}
