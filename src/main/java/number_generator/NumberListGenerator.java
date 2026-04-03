package number_generator;

import number_generator.wrappers.NumberCount;

import java.util.List;

public interface NumberListGenerator {
    List<Integer> generate(NumberCount numberCount);
}
