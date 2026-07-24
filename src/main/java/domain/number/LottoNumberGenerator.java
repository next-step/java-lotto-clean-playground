package domain.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator {
    private static final int LOTTO_NUMBER_COUNT = 6;

    public List<Integer> generate() {
        List<Integer> candidateNumbers = LottoNumberCandidates.createAll();
        Collections.shuffle(candidateNumbers);
        return selectSortedNumbers(candidateNumbers);
    }

    private List<Integer> selectSortedNumbers(List<Integer> candidateNumbers) {
        List<Integer> selectedNumbers = selectNumbers(candidateNumbers);
        Collections.sort(selectedNumbers);
        return selectedNumbers;
    }

    private List<Integer> selectNumbers(List<Integer> candidateNumbers) {
        return new ArrayList<>(candidateNumbers.subList(0, LOTTO_NUMBER_COUNT));
    }
}
