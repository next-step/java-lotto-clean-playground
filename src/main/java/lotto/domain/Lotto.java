package lotto.domain;

import java.util.List;

import lotto.Constant;
import lotto.generator.NumberGenerator;
import lotto.message.ErrorMessage;

public class Lotto {

    private final NumberGenerator numberGenerator;

    private List<Integer> lottoNums;

    public Lotto(NumberGenerator numberGenerator){
        this.numberGenerator = numberGenerator;
        lottoNums = numberGenerator.generateLottoNum();     // 생성자에서 어디까지의 역할을 해야하는가?
        validateLotto();
    }

    public List<Integer> getLottoNums(){
        return lottoNums;
    }

    private void validateLotto(){
        if(lottoNums.size() != Constant.LOTTO_NUM_COUNT){
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUM.getMessage());
        }
    }
}
