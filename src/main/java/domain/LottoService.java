package domain;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class LottoService {
    public Lotto parseLottoAnswer(String lottoAnswer) {
        SortedSet<LottoNumber> numbers = new TreeSet<>();
        for (String num : lottoAnswer.split(",")) {
            numbers.add(new LottoNumber(Integer.parseInt(num.trim())));
        }
        return new Lotto(numbers);
    }

    public MatchCount calculateMatchCount(List<Lotto> tickets, Lotto answer, LottoNumber bonusBall) {
        return MatchCount.calculateStatistics(tickets, answer, bonusBall);
    }
}

