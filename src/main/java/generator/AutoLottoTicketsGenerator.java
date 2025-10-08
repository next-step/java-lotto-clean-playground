package generator;

import domain.Lotto;

import java.util.*;

import static domain.Lotto.ALL_LOTTO_NUMBER_SIZE;
import static domain.Lotto.LOTTO_NUMBER_SIZE;

public class AutoLottoTicketsGenerator {

    public List<Lotto> generateAutoLottoTickets(int ticketsCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < ticketsCount; i++) {
            lottos.add(new Lotto(generateSixLottoNumber()));
        }
        return lottos;
    }

    public List<Integer> generateSixLottoNumber() {
        Random random = new Random();
        Set<Integer> LottoNumberSet = new TreeSet<>();

        while (LottoNumberSet.size() < LOTTO_NUMBER_SIZE) {
            LottoNumberSet.add(random.nextInt(ALL_LOTTO_NUMBER_SIZE) + 1);
        }
        List<Integer> lottoNumbers = new ArrayList<>(LottoNumberSet);
        return lottoNumbers;
    }

}
