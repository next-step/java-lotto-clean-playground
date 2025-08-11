package util;

import java.util.ArrayList;
import java.util.Collections;

import vo.Lotto;
import vo.LottoNumber;

public class LottoNumberGenerator {

    private static final int LOTTO_LOWER_BOUND = 1;
    private static final int LOTTO_UPPER_BOUND = 45;
    private static final int LOTTO_LENGTH = 6;

    public static Lotto generateLotto() {
        ArrayList<Integer> allNumbers = createAllNumbers();
        Collections.shuffle(allNumbers);
        ArrayList<Integer> selectedNumbers = selectNumbers(allNumbers);
        ArrayList<LottoNumber> lottoNumbers = LottoNumber.convertFromIntegers(selectedNumbers);
        return new Lotto(lottoNumbers);
    }

    private static ArrayList<Integer> createAllNumbers() {
        ArrayList<Integer> allNumbers = new ArrayList<>();
        for (int i = LOTTO_LOWER_BOUND; i <= LOTTO_UPPER_BOUND; i++) {
            allNumbers.add(i);
        }
        return allNumbers;
    }

    private static ArrayList<Integer> selectNumbers(ArrayList<Integer> allNumbers) {
        ArrayList<Integer> selectedNumbers = new ArrayList<>();
        for (int index = 0; index < LOTTO_LENGTH; index++) {
            selectedNumbers.add(allNumbers.get(index));
        }
        return selectedNumbers;
    }

}
