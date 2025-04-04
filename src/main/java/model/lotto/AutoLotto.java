package model.lotto;

import model.LottoNumber;
import utils.Utils;

import java.util.List;
import java.util.TreeSet;

public class AutoLotto extends Lotto {

    private AutoLotto(TreeSet<LottoNumber> numbers) {
        super(numbers);
    }

    public static AutoLotto of(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = Utils.convertToLottoNumbers(numbers);
        return new AutoLotto(new TreeSet<>(lottoNumbers));
    }
}
