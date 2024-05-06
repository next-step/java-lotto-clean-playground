package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BonusNumberTest {
    @Test
    void bonus_ValidInput() {
        String bonusNumberStr = "10";
        BonusNumber bonusNumber = new BonusNumber(bonusNumberStr);

        assertEquals(10, bonusNumber.getBonusNumber());
    }

    @Test
    void bonus_InvalidInput() {
        String bonusNumberStr = "ABC";
        assertThrows(NumberFormatException.class, () -> new BonusNumber(bonusNumberStr));
    }
}