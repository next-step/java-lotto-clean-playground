package view;

import constants.ErrorMessageConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

class InputViewTest {
    @ParameterizedTest
    @DisplayName("구입금액으로 정수 1개 외 다른 입력값은 안 받는다")
    @ValueSource(strings = {"100 100", "asdf", "10.0"})
    void testInvalidInput(String userInput) {
        //given
        Scanner scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));
        InputView inputView = new InputView(scanner);

        // when
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, inputView::getUserCashInput);

        // then
        Assertions.assertEquals(ErrorMessageConstants.NOT_A_SINGLE_NUMBER, exception.getMessage());
    }

    @Test
    @DisplayName("구입금액으로 정수 1개를 받는다")
    void testInvalidInput() {
        //given
        Scanner scanner = new Scanner(new ByteArrayInputStream("1000".getBytes()));
        InputView inputView = new InputView(scanner);

        // when
        Assertions.assertDoesNotThrow(inputView::getUserCashInput);
    }
}
