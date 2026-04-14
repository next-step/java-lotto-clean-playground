package number_generator;

import number_generator.wrappers.Count;

import java.util.List;

public interface NumberListGenerator {
    List<Integer> generateDistinctSortedNumbers(Count count, int min, int max);
}
