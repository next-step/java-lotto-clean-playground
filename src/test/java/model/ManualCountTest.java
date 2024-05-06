package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManualCountTest {

    @Test
    void validManualCount() {
        String manualCountStr = "5";
        ManualCount manualCount = new ManualCount(manualCountStr);

        assertEquals(5, manualCount.getManualCount());
    }

    @Test
    void invalidInput() {
        String manualCountStr = "abc";
        assertThrows(NumberFormatException.class, () -> new ManualCount(manualCountStr));
    }

    @Test
    void getManualCount() {
        String manualCountStr = "10";
        ManualCount manualCount = new ManualCount(manualCountStr);

        assertEquals(10, manualCount.getManualCount());
    }

    @Test
    void convertStringToInt_ValidInput() {
        ManualCount manualCount = new ManualCount();
        assertEquals(15, manualCount.convertStringToInt("15"));
    }

    @Test
    void convertStringToInt_InvalidInput() {
        ManualCount manualCount = new ManualCount();
        assertThrows(NumberFormatException.class, () -> manualCount.convertStringToInt("abc"));
    }

}