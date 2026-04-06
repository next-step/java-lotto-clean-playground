package number_generator;

import java.util.List;

public interface NumberListGenerator {
    List<Integer> generateDistinctSortedNumbers(Count count, int min, int max);
}
