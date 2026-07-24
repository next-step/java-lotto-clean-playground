package mission1;

import domain.LottoSystem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DisplayName("로또 테스트")
public class Mission1TestCode {
    @Test
    @DisplayName(" [테스트 코드] : 만들어진 배열이 오름차순으로 등록이 되는지 확인")
    void testCode_1부터_6사이의_무작위로_섞인_배열이_정렬되서_나오는지_확인(){
        LottoSystem lottoSystem = new LottoSystem(1000, new FixedLottoNumberGenerator(List.of(4,1,3,6,2,5)));
        assertEquals(List.of(1,2,3,4,5,6), lottoSystem.getPurchasedLottoNumbers().get(0));
    }
}
