package domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {
  @Test
  @DisplayName("만들어진 List가 오름차순으로 등록이 되는지 확인")
  void test_1부터_6사이의_무작위로_섞인_배열이_정렬되서_나오는지_확인(){
    assertEquals(List.of(1,2,3,4,5,6), new Lotto(List.of(4,2,1,3,5,6)).getLottoNumbers());
  }

  @Test
  @DisplayName(" [테스트코드] : 당첨 번호의 갯수는 LOTTO_MAXIMUM_COUNT(=6)개 미만이거나 초과한다면 예외발생")
  void test_입력한_당첨번호의_갯수가_LOTTO_MAXIMUM_COUNT개를_지키지_않으면_예외발생(){
    assertAll(
        ()->assertThrows(IllegalArgumentException.class, ()->new Lotto(List.of(1,2,3,4,5,6,7,8)).getLottoNumbers()),
        ()->assertThrows(IllegalArgumentException.class, ()->new Lotto(List.of(1,2,3)).getLottoNumbers())
    );
  }

  @Test
  @DisplayName(" [테스트 코드] : 사용자는 당첨 번호에서 동일한 번호를 입력할 수 없다.")
  void test_입력된_번호에서_중복된_번호가_존재하면_예외발생(){
    assertThrows(IllegalArgumentException.class, ()-> new Lotto(List.of(1,1,1,1,1,1)).getLottoNumbers());
  }


  @Test
  @DisplayName(" [테스트 코드] : 로또 번호의 범위는 MIN_NUMBER(=1)보다 작거나 MAX_NUMBER(=45)보다 클 수 없다.")
  void test_입력된_번호의_범위가_MIN_NUMBER_보다_작거나_MAX_NUMBER_보다_큰_경우_예외발생(){
    assertAll(
        ()->assertThrows(IllegalArgumentException.class, ()-> new Lotto(List.of(50,49,48,47,46,45)).getLottoNumbers()),
        ()->assertThrows(IllegalArgumentException.class, ()-> new Lotto(List.of(-5,-4,-3,-2,- 1, 0)).getLottoNumbers())
    );
  }
}
