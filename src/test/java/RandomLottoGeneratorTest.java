import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

class RandomLottoGeneratorTest {

    @RepeatedTest(1000)
    void test() {
        int result = new Random().nextInt(1, 45);

        Assertions.assertThat(result).isBetween(1, 45);
    }

}