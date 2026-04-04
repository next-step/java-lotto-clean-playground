package number_generator;

import domain.LottoConstants;
import number_generator.wrappers.NumberCount;

import java.util.*;

import static domain.LottoConstants.LOWER_BOUND;
import static domain.LottoConstants.UPPER_BOUND;

public class LottoRandomNumberListGenerator implements LottoNumberListGenerator {
    private final Random random = new Random();

    @Override
    public List<Integer> generate() {
        HashSet<Integer> lottoNumberSet = new HashSet<>();

        while (lottoNumberSet.size() < LottoConstants.TICKET_LENGTH) {
            lottoNumberSet.add(random.nextInt(LOWER_BOUND, UPPER_BOUND));
        }

        List<Integer> lottoNumberList = new ArrayList<>(lottoNumberSet);
        lottoNumberList.sort(Comparator.naturalOrder());

        return lottoNumberList;
    }
}
