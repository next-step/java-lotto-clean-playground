package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottosTest {

    private Lottos lottos;
    @BeforeEach
    void setUp(){
        lottos = new Lottos(3);
    }

    @Test
    void 구입한_개수만큼_로또_자동_생성되는지_테스트(){
        List<Lotto> lottoList = lottos.getLottos();

        //구입한 개수만큼 생성되는지
        assertEquals(3, lottoList.size(), "로또 3개가 생성되었습니다.");
    }

    @Test
    void 생성된_로또_번호에대한_테스트(){
        List<Lotto> lottoList = lottos.getLottos();

        //생성된 로또가 6개의 숫자를 가지는지
        for (Lotto lotto : lottoList){
            assertEquals(6, lotto.getLottoNumbers().size(), "각 로또는 6개의 번호를 가져야 합니다.");
        }

        //생성된 로또의 번호가 1~45사이인지
        for (Lotto lotto : lottoList){
            List<Integer> lottoNumbers = lotto.getLottoNumbers();
            for(Integer number : lottoNumbers){
                assertTrue(number >= 1 && number<=45, "로또 번호는 1이상 45이하입니다.");
            }
        }

        //생성된 로또의 번호에 중복이 없는지
        for (Lotto lotto : lottoList){
            long uniqueCount = lotto.getLottoNumbers().stream().distinct().count();
            assertEquals(6, uniqueCount, "로또 번호는 중복이 없습니다.");
        }

    }
}