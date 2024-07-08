import java.util.Collections;
import java.util.List;

public class WinningNumbers {
    private List<Integer> numbers;

    public WinningNumbers(List<Integer> numbers) {
        Collections.sort(numbers);
        this.numbers = numbers;
    }

    public int countMatchingNumbers(Lotto lotto) {
        List<Integer> lottoNumbers = lotto.getNumbers();
        int matchCount = 0;
        for (int number : lottoNumbers) {
            if (numbers.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }
}
