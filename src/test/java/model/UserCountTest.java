package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserCountTest {

    @Test
    void validPrice() {
        String priceStr = "3000";
        UserCount userCount = new UserCount(priceStr);

        assertEquals(3000, userCount.getPrice());
        assertEquals(3, userCount.getCount());
    }

    @Test
    void invalidZeroPrice() {
        String priceStr = "0";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UserCount(priceStr));
        assertEquals("[ERROR] 0원 이하는 구매가 불가능합니다", exception.getMessage());
    }

    @Test
    void invalidNegativePrice() {
        String priceStr = "-5000";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UserCount(priceStr));
        assertEquals("[ERROR] 0원 이하는 구매가 불가능합니다", exception.getMessage());
    }

    @Test
    void invalidNonMultipleOf1000() {
        String priceStr = "2500";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new UserCount(priceStr));
        assertEquals("[ERROR] 구입 금액은 1000원 단위로 입력되어야 합니다.", exception.getMessage());
    }

    @Test
    void calculateCount_ValidPrice() {
        UserCount userCount = new UserCount("4000");
        assertEquals(4, userCount.getCount());
    }

    @Test
    void convertStringToInt_ValidInput() {
        UserCount userCount = new UserCount();
        assertEquals(5000, userCount.convertStringToInt("5000"));
    }

    @Test
    void convertStringToInt_InvalidInput() {
        UserCount userCount = new UserCount();
        assertThrows(NumberFormatException.class, () -> userCount.convertStringToInt("abc"));
    }

    @Test
    void validateRange_ValidPrice() {
        UserCount userCount = new UserCount();
        assertDoesNotThrow(() -> userCount.validateRange(2000));
    }

    @Test
    void validateRange_InvalidZeroPrice() {
        UserCount userCount = new UserCount();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userCount.validateRange(0));
        assertEquals("[ERROR] 0원 이하는 구매가 불가능합니다", exception.getMessage());
    }

    @Test
    void validateUnit_ValidPrice() {
        UserCount userCount = new UserCount();
        assertDoesNotThrow(() -> userCount.validateUnit(3000));
    }

    @Test
    void validateUnit_InvalidNonMultipleOf1000() {
        UserCount userCount = new UserCount();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userCount.validateUnit(2500));
        assertEquals("[ERROR] 구입 금액은 1000원 단위로 입력되어야 합니다.", exception.getMessage());
    }
}
