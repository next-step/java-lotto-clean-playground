package domain;

import java.util.*;

public class Lotto
{
    private final List<Integer> lottoNumber;

    public Lotto(List<Integer> numbers)
    {
        if (numbers.size() != LottoConstants.LOTTO_COUNT)
            throw new IllegalArgumentException(String.format("로또 번호는 %d개여야 합니다.", LottoConstants.LOTTO_COUNT));

        if (checkDuplicate(numbers))
            throw new IllegalArgumentException("로또 번호에 중복이 없어야 합니다.");

        this.lottoNumber = numbers;
    }

    // 중복 숫자 체크
    private boolean checkDuplicate(List<Integer> numList)
    {
        Set<Integer> numSet = new HashSet<>(numList);
        return numSet.size() != numList.size();
    }

    // 현재 로또와 당첨 로또의 공통 숫자 개수 반환
    public int getCorrectCount(Lotto goalLotto)
    {
        return (int) lottoNumber.stream()
                .filter(goalLotto.getLottoNumbers()::contains)
                .count();
    }

    // 현재 로또와 당첨 로또의 2등 보너스번호 비교
    public boolean getIsBonus(int bonusNumber)
    {
        return lottoNumber.stream()
                .anyMatch(num -> num == bonusNumber);
    }

    // 저장된 로또 숫자 반환
    public List<Integer> getLottoNumbers()
    {
        return lottoNumber;
    }
}