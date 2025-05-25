package lotto;

import lotto.input.PassivityNumberInput;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class PassivityNumberInputTest {

    @Test
    void passivityLottoCount() {
        String input = "7\n-1\n2\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        PassivityNumberInput passivityNumberInput = new PassivityNumberInput(5);
        int result = passivityNumberInput.passivityLottoCount();

        assertEquals(2, result, "수동 개수는 2개 반한됩니다");
    }

}