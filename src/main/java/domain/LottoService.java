package domain;

import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LottoService {
    public Lotto parseLottoAnswer(String lottoAnswer) {
        SortedSet<LottoNumber> numbers = new TreeSet<>();
        for (String num : lottoAnswer.split(",")) {
            numbers.add(new LottoNumber(Integer.parseInt(num.trim())));
        }
        return new Lotto(numbers);
    }

    public MatchCount calculateMatchCount(List<Lotto> tickets, Lotto answer, LottoNumber bonusBall) {
        MatchCount matchCount = MatchCount.countAllMatches(tickets, answer);
        MatchCount bonusCount = MatchCount.countBonusBallMatches(tickets, answer, bonusBall);
        matchCount.merge(bonusCount);
        return matchCount;
    }
}

