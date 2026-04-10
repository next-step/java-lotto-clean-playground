import model.LottoRank;
import model.LottoResult;
import model.LottoShop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LottoShopTest {
    @Test
    @DisplayName("가격을 입력받았을때 올바른 자동티켓/수동티켓을 반환하는지")
    public void getAmountTest() {
        //given:2만월을 받고, 2개의 수동 티켓을 받음
        int inputMoney = 20000;
        LottoShop money = new LottoShop(inputMoney, 2);
        //when: 2개의 수동 티켓/18개의 자동티켓/총티켓 20
        int TrueManualamount = 2;
        int TrueAutoamount = 18;
        int TrueTotalAmount = 20;
        //then:수동티켓/자동티켓/총 티켓 갯수 일치하는지 확인해주기
        Assertions.assertEquals(TrueManualamount, money.getManualAmount());
        Assertions.assertEquals(TrueAutoamount, money.getAutoAmount());
        Assertions.assertEquals(TrueTotalAmount, money.getAmount());
    }

    @Test
    @DisplayName("수익률 계산이 올바른지 테스트")
    public void calculateRateOfReturnTest() {
        //given 받는 돈이 20000원,그중 수익이 10000원이라고 하자
        LottoShop money = new LottoShop(20000, 4);
        int profit = 10000;
        //when:money에 있는 수익률 계산 메서드를 이용할때
        double ror = money.calculateRateOfReturn(profit);
        //then:10000/20000과 동일한지에 대한 검증을 함.
        Assertions.assertEquals(0.5, ror);
    }

    @Test
    @DisplayName("여러 등수가 제대로 환산되는지")
    public void getWinningAmountTest() {
        //given:등수별로 하나씩 다 주어진다고 가정했을대
        LottoShop money = new LottoShop(20000, 1);
        Map<LottoRank, Integer> status = new HashMap<>();
        //when: 모든 status에 put해준다
        status.put(LottoRank.THREE, 1);
        status.put(LottoRank.FOUR, 1);
        status.put(LottoRank.FIVE, 1);
        status.put(LottoRank.SIX, 1);
        //then: 결과 값과 내가 예측한 값이 동일해야한다.
        LottoResult lottoResult = new LottoResult();
        int result = lottoResult.getWinningAmount(status);
        int expect = LottoRank.THREE.getWinningMoney() * 1 +
                LottoRank.FOUR.getWinningMoney() * 1 +
                LottoRank.FIVE.getWinningMoney() * 1 +
                LottoRank.SIX.getWinningMoney() * 1;

        Assertions.assertEquals(result, expect);
    }

    @Test
    @DisplayName("수동 구매 로또 수, 유효검증 테스트")
    public void validateManualAmount_success(){
        //Given:개수가 4인 money와 유효한 개수 4를 가질때
        int money=4000;
        int validateCount=4;
        //When:새로운 객체 생성을 통해 가능한 manualAmount 개수를 받음
        LottoShop lottoShop =new LottoShop(money, validateCount);
        //Then:유효한지에 대한 검증
        Assertions.assertDoesNotThrow(() -> {
            lottoShop.validateManualAmount(validateCount);
        });
    }

    @Test
    @DisplayName("수동 로또 개수가 구매 금액을 초과 예외")
    void validateManualAmount_fail() {
        int money = 4000;   // 최대 4장 가능
        int manualCount = 5; // 초과

        LottoShop lottoShop = new LottoShop(money,manualCount);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            lottoShop.validateManualAmount(manualCount);
        });
    }

}
