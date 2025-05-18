package service.generator;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import service.LottoGenerator;
import service.NumberGenerator;
import service.TicketGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGeneratorImpl implements LottoGenerator {

    private final NumberGenerator numberGenerator;
    private final TicketGenerator ticketGenerator;

    public LottoGeneratorImpl(NumberGenerator numberGenerator, TicketGenerator ticketGenerator) {
        this.numberGenerator = numberGenerator;
        this.ticketGenerator = ticketGenerator;
    }

    @Override
    public Lottos generate(int amount) {
        int count = ticketGenerator.generate(amount);
        return generateByCount(count);
    }

    @Override
    public Lottos generateByCount(int count) {
        List<Lotto> tickets = IntStream.range(0, count)
                .mapToObj(i -> toLotto(numberGenerator.generate()))
                .collect(Collectors.toList());

        return new Lottos(tickets);
    }

    private Lotto toLotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }
}
