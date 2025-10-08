package test.domain;

import domain.Lotto;
import domain.LottoPrizeCalculator;
import domain.MatchReward;
import domain.value.BonusNumber;
import domain.value.Money;
import domain.value.WinningNumbers;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LottoPrizeCalculatorTest {

    @Test
    void 각_등수가_한장씩_있으면_수익률이_정확하다() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        Lotto threeMatchTicket = new Lotto(List.of(1, 2, 3, 40, 41, 42));
        Lotto fourMatchTicket = new Lotto(List.of(1, 2, 3, 4, 40, 41));
        Lotto fiveMatchTicket = new Lotto(List.of(1, 2, 3, 4, 5, 40));
        Lotto fiveBonusMatchTicket = new Lotto(List.of(1, 2, 3, 4, 5, 7));
        Lotto sixMatchTicket = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        List<Lotto> purchasedTickets = List.of(
                threeMatchTicket, fourMatchTicket, fiveMatchTicket, fiveBonusMatchTicket, sixMatchTicket
        );
        Money paidAmount = new Money(5000);

        LottoPrizeCalculator lottoPrizeCalculator = new LottoPrizeCalculator();

        // when
        LottoPrizeCalculator.Result result =
                lottoPrizeCalculator.calculate(purchasedTickets, winningNumbers, paidAmount, bonusNumber);

        // then
        assertEquals(1, result.threeMatchCount);
        assertEquals(1, result.fourMatchCount);
        assertEquals(1, result.fiveMatchCount);
        assertEquals(1, result.fiveBonusMatchCount);
        assertEquals(1, result.sixMatchCount);

        int expectedTotalPrizeAmount =
                MatchReward.THREE.getPrize()
                        + MatchReward.FOUR.getPrize()
                        + MatchReward.FIVE.getPrize()
                        + MatchReward.BONUSFIVE.getPrize()
                        + MatchReward.SIX.getPrize();
        assertEquals(expectedTotalPrizeAmount, result.totalPrizeAmount);

        float expectedReturnRate = (float) expectedTotalPrizeAmount / paidAmount.value();
        assertEquals(expectedReturnRate, result.returnRate, 0.0001f);
    }

    @Test
    void 일치하지_않거나_보너스만_있는_경우는_당첨금에_반영되지_않는다() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        Lotto noMatchTicket = new Lotto(List.of(10, 11, 12, 13, 14, 15));
        Lotto bonusOnlyTicket = new Lotto(List.of(7, 20, 21, 22, 23, 24));

        List<Lotto> purchasedTickets = List.of(noMatchTicket, bonusOnlyTicket);
        Money paidAmount = new Money(2000);

        LottoPrizeCalculator lottoPrizeCalculator = new LottoPrizeCalculator();

        // when
        LottoPrizeCalculator.Result result =
                lottoPrizeCalculator.calculate(purchasedTickets, winningNumbers, paidAmount, bonusNumber);

        // then
        assertEquals(0, result.threeMatchCount);
        assertEquals(0, result.fourMatchCount);
        assertEquals(0, result.fiveMatchCount);
        assertEquals(0, result.fiveBonusMatchCount);
        assertEquals(0, result.sixMatchCount);
        assertEquals(0, result.totalPrizeAmount);
        assertEquals(0.0f, result.returnRate, 0.0f);
    }

    @Test
    void 같은_등수가_여러장_일때_총합이_정확하다() {
        // given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));
        BonusNumber bonusNumber = new BonusNumber(7);

        Lotto fourMatchTicketA = new Lotto(List.of(1, 2, 3, 4, 40, 41));
        Lotto fourMatchTicketB = new Lotto(List.of(1, 2, 3, 4, 42, 43));
        Lotto fourMatchTicketC = new Lotto(List.of(1, 2, 3, 4, 44, 45));

        List<Lotto> purchasedTickets = List.of(fourMatchTicketA, fourMatchTicketB, fourMatchTicketC);
        Money paidAmount = new Money(3000);

        LottoPrizeCalculator lottoPrizeCalculator = new LottoPrizeCalculator();

        // when
        LottoPrizeCalculator.Result result =
                lottoPrizeCalculator.calculate(purchasedTickets, winningNumbers, paidAmount, bonusNumber);

        // then
        assertEquals(0, result.threeMatchCount);
        assertEquals(3, result.fourMatchCount);
        assertEquals(0, result.fiveMatchCount);
        assertEquals(0, result.fiveBonusMatchCount);
        assertEquals(0, result.sixMatchCount);

        int expectedTotalPrizeAmount = 3 * MatchReward.FOUR.getPrize();
        assertEquals(expectedTotalPrizeAmount, result.totalPrizeAmount);

        float expectedReturnRate = (float) expectedTotalPrizeAmount / paidAmount.value();
        assertEquals(expectedReturnRate, result.returnRate);
    }
}
