package domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FakeGenerateRandomNumber implements RandomNumber {

    @Override
    public List<Integer> generateNumber() {
        return Stream.of(11, 23, 32, 44, 5, 16)
                .collect(Collectors.toList());
    }
}
