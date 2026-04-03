package number_generator;

import number_generator.wrappers.NumberCount;

import java.util.*;

public class RandomLottoNumberListGenerator implements NumberListGenerator {
    private static final Integer UPPER_BOUND = 46;
    private static final Integer LOWER_BOUND = 1;

    private final Random random = new Random();

    @Override
    public List<Integer> generate(NumberCount numberCount) {
        HashSet<Integer> lottoNumberSet = new HashSet<>();

        while (lottoNumberSet.size() < numberCount.getValue()) {
            lottoNumberSet.add(random.nextInt(LOWER_BOUND, UPPER_BOUND));
        }

        List<Integer> lottoNumberList = new ArrayList<>(lottoNumberSet);
        lottoNumberList.sort(Comparator.naturalOrder());

        return  lottoNumberList;
    }
}
