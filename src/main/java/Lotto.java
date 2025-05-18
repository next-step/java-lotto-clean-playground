import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("로또 당첨 번호의 개수는 6개 입니다.");
        }

        if (numbers == null){
            throw new IllegalArgumentException("로또 번호 리스트가 null일 수 없습니다.");
        }

        // 생성자에서 리스트를 그대로 할당하면 외부에서 변경할 수 있기 때문에 복사
        this.numbers = Collections.unmodifiableList(new ArrayList<>(numbers));
    }

    public Rank match(WinningNumbers winningNumbers) {
        int matchCount = countMatch(winningNumbers);
        boolean bonusMatch = hasBonusMatch(winningNumbers);

        return Rank.valueOf(matchCount, bonusMatch);
    }

    public int countMatch(WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }

    private boolean hasBonusMatch(WinningNumbers winningNumbers) {
        return numbers.stream()
                .anyMatch(winningNumbers::isBonusBall);
    }


    public List<LottoNumber> getNumbers() {
        return numbers;
    }

}
