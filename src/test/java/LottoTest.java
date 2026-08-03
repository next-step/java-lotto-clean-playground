import model.Lotto;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;

class LottoTest {
    @Test
    void checkDuplicatedNumber(){
        Lotto lotto=new Lotto();
        int originalSize=lotto.getLottoList().size();
        int removeDuplicatedNumberSize=new HashSet<>(lotto.getLottoList()).size();

        assertThat(originalSize).isEqualTo(removeDuplicatedNumberSize);

        assertEquals(originalSize,removeDuplicatedNumberSize);
    }
}
