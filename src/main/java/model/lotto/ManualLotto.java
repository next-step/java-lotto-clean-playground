package model.lotto;


import java.util.List;
import java.util.TreeSet;

import static utils.LottoConstants.*;
import static utils.LottoConstants.LOTTO_NUMBER_COUNT;

public class ManualLotto extends Lotto {

    public ManualLotto(TreeSet<Integer> numbers) {
        super(numbers);
    }

    public static ManualLotto of(List<Integer> numbers) {
        TreeSet<Integer> lottoNumbers = convertLottoNumbersToTreeSet(numbers);
        validateLottoNumbers(lottoNumbers);
        return new ManualLotto(lottoNumbers);
    }

    private static TreeSet<Integer> convertLottoNumbersToTreeSet(List<Integer> numbers) {
        TreeSet<Integer> lottoNumbers = new TreeSet<>();
        for (Integer number : numbers) {
            addLottoNumbers(number, lottoNumbers);
        }
        return lottoNumbers;
    }

    private static void addLottoNumbers(Integer number, TreeSet<Integer> lottoNumbers) {
        if(!lottoNumbers.add(number)) {
            throw new IllegalArgumentException("로또 번호는 중복이 없어야 합니다!");
        }
    }

    private static void validateLottoNumbers(TreeSet<Integer> lottoNumbers) {
        validateLottoSize(lottoNumbers);
        validateLottoBound(lottoNumbers);
    }

    private static void validateLottoBound(TreeSet<Integer> lottoNumbers) {
        boolean hasOutOfBoundNumber = lottoNumbers.stream().anyMatch(number -> number > LOTTO_MAX_NUMBER || number < LOTTO_MIN_NUMBER);
        if (hasOutOfBoundNumber) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_MIN_NUMBER + "이상 " + LOTTO_MAX_NUMBER + "이하의 정수입니다!");
        }
    }

    private static void validateLottoSize(TreeSet<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 " + LOTTO_NUMBER_COUNT + "자리 입니다!");
        }
    }
}
