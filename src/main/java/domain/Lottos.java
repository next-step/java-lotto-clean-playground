package domain;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lottos
{
    private final List<Lotto> lottos = new ArrayList<>();
    private int canBuyCount = 0;
    private int manualBuyCount = 0;
    private int autoBuyCount = 0;

    public void payCost(int cost)
    {
        this.canBuyCount += cost / LottoConstants.LOTTO_PRICE;
    }

    // 수동 로또 저장 처리
    public void buyManualLottos(int manualBuyCount, List<List<Integer>> manualLottos)
    {
        if (manualBuyCount > this.canBuyCount)
            throw new IllegalArgumentException("지불한 금액보다 더 많은 로또는 살 수 없습니다.");

        this.manualBuyCount += manualBuyCount;
        this.autoBuyCount = this.canBuyCount - manualBuyCount;
        this.canBuyCount = 0;

        for (List<Integer> manualLotto : manualLottos)
            lottos.add(new Lotto(manualLotto));
    }

    private List<Integer> generateRandomNumbers()
    {
        List<Integer> numbers = new ArrayList<>();
        for (int i=1; i<=LottoConstants.MAX_LOTTO_NUMBER; i++)
            numbers.add(i);

        Collections.shuffle(numbers);
        return numbers;
    }

    private List<Integer> generateLottoNumbers()
    {
        return generateRandomNumbers().subList(0, LottoConstants.LOTTO_COUNT).stream()
                .sorted()
                .collect(Collectors.toList());
    }

    // 자동 로또 생성 및 저장 처리
    public void buyAutoLottos()
    {
        for (int i=0; i<autoBuyCount; i++)
            lottos.add(new Lotto(generateLottoNumbers()));
    }

    private Rank getCorrectCount(Lotto lotto, List<Integer> lastweekGoalNumber, int lastweekBonusGoalNumber) {
        int correctCount = (int) lotto.getLottoNumbers().stream()
                .filter(lastweekGoalNumber::contains)
                .count();
        boolean isBonus = lotto.getLottoNumbers().contains(lastweekBonusGoalNumber);

        return Rank.calculateMatchCount(correctCount, isBonus);
    }

    // 인자로 받은 모든 로또에 대해 일치하는 개수 체크
    public Map<Rank, Long> getTotalCorrectCount(List<Integer> lastweekGoalNumber, int lastweekBonusGoalNumber) {
        Map<Rank, Long> result = new EnumMap<>(Rank.class);

        for (Rank rank : Rank.values())
            result.put(rank, 0L);

        for (Lotto lotto : lottos) {
            Rank rank = getCorrectCount(lotto, lastweekGoalNumber, lastweekBonusGoalNumber);
            result.put(rank, result.get(rank) + 1);
        }

        return result;
    }

    public List<Lotto> getLottos()
    {
        return lottos;
    }

    public List<Integer> getLottoCount()
    {
        return Arrays.asList(manualBuyCount, autoBuyCount);
    }

    public int getCost()
    {
        return (manualBuyCount+autoBuyCount)*LottoConstants.LOTTO_PRICE;
    }
}