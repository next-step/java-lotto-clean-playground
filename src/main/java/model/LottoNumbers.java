package model;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static model.LottoConstraints.LOTTO_SIZE;

public class LottoNumbers {

    private static final Comparator<LottoNumber> NUMBER_COMPARATOR = Comparator.comparingInt(LottoNumber::value);

    private final SortedSet<LottoNumber> lottoNumbers;

    public LottoNumbers(Set<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers.size());
        this.lottoNumbers = new TreeSet<>(NUMBER_COMPARATOR);
        this.lottoNumbers.addAll(lottoNumbers);
    }

    public LottoNumbers getCopy() {
        return new LottoNumbers(Set.copyOf(this.lottoNumbers));
    }

    public int getEqualNumbersCount(LottoNumbers otherLottoNumbers) {
        LottoNumberMatcher lottoNumberMatcher = new LottoNumberMatcher();

        for (LottoNumber lottoNumber : this.lottoNumbers) {
            lottoNumberMatcher.increaseIfMatch(() -> otherLottoNumbers.contains(lottoNumber));
        }

        return lottoNumberMatcher.getNumberEqualCount();
    }

    public boolean hasEqualNumberWithBonusBall(LottoNumber bonusBall) {
        return this.lottoNumbers.contains(bonusBall);
    }

    private boolean contains(LottoNumber lottoNumber) {
        return this.lottoNumbers.contains(lottoNumber);
    }

    private void validateSize(int size) {
        if (isIllegalSize(size)) {
            throw new IllegalArgumentException(String.format("로또 번호 갯수는 %d개여야 합니다: %d", LOTTO_SIZE, size));
        }
    }

    private boolean isIllegalSize(int size) {
        return size != LOTTO_SIZE;
    }

    @Override
    public String toString() {
        return lottoNumbers.toString();
    }

}
