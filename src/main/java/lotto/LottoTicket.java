package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LottoTicket {
    public static final int SIZE = 6;

    private final List<LottoNumber> numbers;

    public LottoTicket(List<LottoNumber> numbers) {
        if (numbers == null || numbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 티켓은 정확히 " + SIZE + "개의 번호가 필요합니다.");
        }
        List<LottoNumber> copy = new ArrayList<>(numbers);
        copy.sort(Comparator.naturalOrder());
        this.numbers = Collections.unmodifiableList(copy);
    }

    public List<LottoNumber> getNumbers() {
        return numbers;
    }

    public boolean contains(LottoNumber target) {
        return numbers.contains(target);
    }

    public int countMatches(LottoTicket other) {
        int matchCount = 0;
        for (LottoNumber n : numbers) {
            if (other.contains(n)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}


