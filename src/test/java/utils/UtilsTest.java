package utils;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UtilsTest {

    @Test
    void 쉼표로_구분된_숫자들은_구분자로_나뉘어_정수_리스트로_반환되어야_힌다() {
        String string = "1,2,3,4,5,6,7,8";
        List<Integer> integers = Utils.parseCommaSeparatedInts(string);

        assertThat(integers).containsExactly(1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Test
    void 쉼표_사이에_공백이_있어도_정수_리스트로_반환되어야_한다() {
        String string = "1,2 ,3, 4, 5,6,7,8 ";
        List<Integer> integers = Utils.parseCommaSeparatedInts(string);

        assertThat(integers).containsExactly(1, 2, 3, 4, 5, 6, 7, 8);
    }
}
