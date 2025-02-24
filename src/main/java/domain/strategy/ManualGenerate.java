package domain.strategy;

import domain.model.Lotto;
import domain.util.LottoUtils;

import java.util.Collections;
import java.util.List;

public class ManualGenerate implements LottoStrategy{
    @Override
    public Lotto generateLotto(List<Integer> manualNumbers){
        Collections.sort(manualNumbers);
        return new Lotto(LottoUtils.convertToLottoNumbers(manualNumbers));
    }
}
