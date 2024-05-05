package domain;

import model.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("소비자 객체 테스트")
class ConsumerTest {

    Consumer consumer = new Consumer(14000);

    @Test
    void 소유한_로또들을_당첨번호와_비교한다() {

        //Lotto의 번호들이 랜던값으로 생성되어
        //테스트를 진행하지 못하고 있다.
    }
}