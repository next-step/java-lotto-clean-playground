package number_generator;

import java.util.*;

public class RandomNumberListGenerator implements NumberListGenerator {
    private static final Random random = new Random();

    @Override
    public List<Integer> generateDistinctSortedNumbers(Count count, int min, int max) {
        HashSet<Integer> numberSet = new HashSet<>();

        while (numberSet.size() < count.getValue()) {
            numberSet.add(random.nextInt(min, max));
        }

        List<Integer> numberList = new ArrayList<>(numberSet);
        numberList.sort(Comparator.naturalOrder());

        return numberList;
    }
}
