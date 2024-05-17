package fakeTest;

import domain.RandomNumber;

import java.util.List;

public class FakeGenerateRandomNumber implements RandomNumber {

    @Override
    public List<Integer> generateRandomNumber() {
        return List.of(11, 23, 32, 44, 5, 16);
    }
}
