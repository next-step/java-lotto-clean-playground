package lotto.domain;

import java.util.List;

import lotto.Constant;
import lotto.generator.NumberGenerator;
import lotto.message.ErrorMessage;

public class Lotto {

    private List<Integer> lottoNums;

    public Lotto(NumberGenerator numberGenerator) {
        lottoNums = numberGenerator.generateLottoNum();     // 생성자에서 어디까지의 역할을 해야하는가?
        validateLottoSize();
    }

    public Lotto(List<Integer> lottoNums) {
        this.lottoNums = lottoNums;
        validateLottoSize();
        validateLottoNum();
    }

    public List<Integer> getLottoNums() {
        return lottoNums;
    }

    private void validateLottoSize() {
        if (lottoNums.size() != Constant.LOTTO_NUM_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUM.getMessage());
        }
    }

    private void validateLottoNum(){
        for(int num : lottoNums){
            if(!(num >= Constant.MIN_NUM && num <= Constant.MAX_NUM)){
                throw new IllegalArgumentException(ErrorMessage.INVALID_NUM.getMessage());
            }
        }
    }
}
