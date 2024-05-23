package domain;

import model.LottoMachine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("로또 기계 테스트")
class LottoMachineTest {

    @Test
    void makeAutoNumber() {

        //랜던값 구현 방법을 고민해보고 찾아 봤지만 구현 방법을 모르겠다.
        //mokito를 이용하면 편리하다는데 이것과 관련하여 피드백을 받고 싶습니다.
    }

    @Test
    void subList_호출시_인덱스_0부터_5까지의_값을_반환한다() {

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> actual = LottoMachine.subList(0, 6);

        assertThat(actual).containsExactlyElementsOf(expected);
    }
}