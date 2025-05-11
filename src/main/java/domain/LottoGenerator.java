package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private final NumberGenerator numberGenerator;
    private final TicketGenerator ticketGenerator;

    public LottoGenerator(NumberGenerator numberGenerator, TicketGenerator ticketGenerator) {
        this.numberGenerator = numberGenerator;
        this.ticketGenerator = ticketGenerator;
    }

    public Lottos generate(int amount) {
        int count = ticketGenerator.generate(amount);

        List<Lotto> tickets = IntStream.range(0, count)
                .mapToObj(i -> toLotto(numberGenerator.generate()))
                .collect(Collectors.toList());

        return new Lottos(tickets);
    }

    private Lotto toLotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new Lotto(new LottoNumbers(lottoNumbers));
    }
}
