package model.lotto;


import model.LottoNumber;
import utils.Utils;

import java.util.List;
import java.util.TreeSet;

import static utils.LottoConstants.*;
import static utils.LottoConstants.LOTTO_NUMBER_COUNT;

public class ManualLotto extends Lotto {

    public ManualLotto(TreeSet<LottoNumber> numbers) {
        super(numbers);
    }

    public static ManualLotto of(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = Utils.convertToLottoNumbers(numbers);

        TreeSet<LottoNumber> lottoNumbersSet = convertLottoNumbersToTreeSet(lottoNumbers);
        validateLottoNumbers(lottoNumbersSet);
        return new ManualLotto(lottoNumbersSet);
    }

    private static TreeSet<LottoNumber> convertLottoNumbersToTreeSet(List<LottoNumber> numbers) {
        TreeSet<LottoNumber> lottoNumbers = new TreeSet<>();
        for (LottoNumber number : numbers) {
            addLottoNumbers(number, lottoNumbers);
        }
        return lottoNumbers;
    }

    private static void addLottoNumbers(LottoNumber number, TreeSet<LottoNumber> lottoNumbers) {
        if(!lottoNumbers.add(number)) {
            throw new IllegalArgumentException("로또 번호는 중복이 없어야 합니다!");
        }
    }

    private static void validateLottoNumbers(TreeSet<LottoNumber> lottoNumbers) {
        validateLottoSize(lottoNumbers);
        validateLottoBound(lottoNumbers);
    }

    private static void validateLottoBound(TreeSet<LottoNumber> lottoNumbers) {
        boolean hasOutOfBoundNumber = lottoNumbers.stream().anyMatch(number -> number.isGraterThan(LOTTO_MAX_NUMBER) || number.isLessThan(LOTTO_MIN_NUMBER));
        if (hasOutOfBoundNumber) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_MIN_NUMBER + "이상 " + LOTTO_MAX_NUMBER + "이하의 정수입니다!");
        }
    }

    private static void validateLottoSize(TreeSet<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBER_COUNT + "자리 입니다!");
        }
    }
}
