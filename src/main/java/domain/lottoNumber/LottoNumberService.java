package domain.lottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberService {
    private final int MIN_LOTTO_NUMBER = 1;
    private final int MAX_LOTTO_NUMBER = 45;
    private final int FIRST_LOTTO_POSITION = 0;
    private final int LAST_LOTTO_POSITION = 6;

    public List<Integer> generateLottoNumberList() {
        List<Integer> lottoNumberList = new ArrayList<>();

        for(int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            lottoNumberList.add(i);
        }

        return lottoNumberList;
    }

    public LottoNumber generateLottoNumber() {
        List<Integer> lottoNumberList = generateLottoNumberList();
        Collections.shuffle(lottoNumberList);
        LottoNumber lottoNumber = new LottoNumber(lottoNumberList.subList(FIRST_LOTTO_POSITION, LAST_LOTTO_POSITION));
        return lottoNumber;
    }
}
