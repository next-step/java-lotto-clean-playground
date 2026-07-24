package domain.lotto;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Win {

    private final List<Integer> lastWeekWinningNumbers;
    private final Map<String, Integer> winningStatics = new LinkedHashMap<>();

    public Win(List<Integer> lastWeekWinningNumbers, List<Lotto> tickets) {
        this.lastWeekWinningNumbers = lastWeekWinningNumbers;
        this.putStatics(tickets);
    }

    private void putStatics(List<Lotto> tickets) {
        for(Integer i = 3; i <= 6; i++) {
            winningStatics.put(i.toString(), 0);
        }

        for (Lotto lotto : tickets) {

            // 몇 개 일치하는지
            Integer key = compare(lotto.getNumbers());

            // 일치 개수에 따른 매핑
            winningStatics.merge(key.toString(), 1, Integer::sum);
        }
    }

    public Map<String, Integer> getWinningStatics() {
        return winningStatics;
    }

    private Integer compare(List<Integer> numbers) {
        return (int) lastWeekWinningNumbers.stream()
                .filter(numbers::contains)
                .count();
    }
}
