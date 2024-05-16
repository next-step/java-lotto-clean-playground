package fakeTest;

import domain.RandomNumber;
import java.util.List;

public class FakeGenerateRandomNumber implements RandomNumber {

    @Override
    public List<Integer> generateNumber() {
        return List.of(11, 23, 32, 44, 5, 16);
    }
}
