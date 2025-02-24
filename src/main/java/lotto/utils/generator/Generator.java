package lotto.utils.generator;

import java.util.Set;

public interface Generator {

    Set<Integer> generate(int start, int end, int size);
}
