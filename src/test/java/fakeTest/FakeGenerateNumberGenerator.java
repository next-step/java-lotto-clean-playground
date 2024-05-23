package fakeTest;

import domain.NumberGenerator;

import java.util.List;

public class FakeGenerateNumberGenerator implements NumberGenerator {

    @Override
    public List<Integer> generateRandomNumber() {
        return List.of(5, 16, 11, 23, 32, 44);
    }
}
