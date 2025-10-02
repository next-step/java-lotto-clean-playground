package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    public static final int LOTTO_TICKET_SIZE = 6;

    private List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        List<Integer>sortedNumbers = sortNumbers(numbers);
        validateSize(sortedNumbers);
        validateDuplicates(sortedNumbers);
        this.numbers = convertToLottoNumbers(sortedNumbers);
    }

    private List<Integer> sortNumbers(List<Integer> numbers) {
        return numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public int countMatches(WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    public boolean bonusMatch(LottoNumber bonusBall) {
        return numbers.contains(bonusBall);
    }

    public String toString() {
        return numbers.toString();
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("당첨 번호는 반드시 " + Lotto.LOTTO_TICKET_SIZE + "개여야 합니다.");
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (Integer number : numbers) {
            lottoNumbers.add(new LottoNumber(number));
        }
        return lottoNumbers;
    }
}
