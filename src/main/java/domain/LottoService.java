package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoService {
    public Lotto parseLottoAnswer(String lottoAnswer) {
        List<LottoNumber> lottoNumbers = Arrays.stream(lottoAnswer.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    public MatchCount calculateMatchCount(List<Lotto> tickets, Lotto answer) {
        return MatchCount.countAllMatches(tickets, answer);
    }
}

