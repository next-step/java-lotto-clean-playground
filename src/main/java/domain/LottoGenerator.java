package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGenerator {

    public Lotto generateAutoLotto(List<Integer> autoNumber) {
        Collections.sort(autoNumber);
        return new Lotto(autoNumber);
    }

    public Lotto generateManualLotto(String input) {
        List<Integer> manualNumber = Arrays.stream(input.split(","))
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(Collectors.toCollection(ArrayList::new));
        return new Lotto(manualNumber);
    }
}
